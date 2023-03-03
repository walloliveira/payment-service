package br.com.walloliveira

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition
import com.tngtech.archunit.library.Architectures
import javax.persistence.Entity
import javax.ws.rs.Path

@AnalyzeClasses(packages = ["br.com.walloliveira"])
class ArchitectureTest {

    internal enum class Layer(val layerName: String, val layerPackage: String) {
        APPLICATION("Application", "..application.."),
        DOMAIN("Domain", "..domain.."),
        INFRASTRUCTURE(
            "Infrastructure",
            "..infrastructure..",
        ),
    }

    @ArchTest
    fun layersShouldHaveAccessOnlyToWhatTheyNeed(importedClasses: JavaClasses?) {
        val layersRule = Architectures.layeredArchitecture()
            .consideringOnlyDependenciesInLayers()
            .layer(Layer.APPLICATION.layerName).definedBy(Layer.APPLICATION.layerPackage)
            .layer(Layer.INFRASTRUCTURE.layerName).definedBy(Layer.INFRASTRUCTURE.layerPackage)
            .layer(Layer.DOMAIN.layerName).definedBy(Layer.DOMAIN.layerPackage)

            .whereLayer(Layer.APPLICATION.layerName).mayOnlyAccessLayers(
                Layer.DOMAIN.layerName,
            )
            .whereLayer(Layer.INFRASTRUCTURE.layerName).mayOnlyAccessLayers(
                Layer.DOMAIN.layerName,
            )
            .whereLayer(Layer.DOMAIN.layerName).mayNotAccessAnyLayer()
        layersRule.check(importedClasses)
    }

    @ArchTest
    fun resourcesShouldHaveSimpleNameEndingWithResource(importedClasses: JavaClasses?) {
        val rule = ArchRuleDefinition
            .classes()
            .that()
            .areAnnotatedWith(Path::class.java)
            .should()
            .haveSimpleNameEndingWith("Resource")
        rule.check(importedClasses)
    }

    @ArchTest
    fun repositoriesShouldHaveSimpleNameEndingWithRepository(importedClasses: JavaClasses?) {
        val rule = ArchRuleDefinition
            .classes()
            .that()
            .resideInAPackage("..repositories..")
            .should()
            .haveSimpleNameEndingWith("Repository")
        rule.check(importedClasses)
    }

    @ArchTest
    fun servicesShouldHaveSimpleNameEndingWithService(importedClasses: JavaClasses?) {
        val rule = ArchRuleDefinition
            .classes()
            .that()
            .resideInAPackage("..services..")
            .should()
            .haveSimpleNameEndingWith("Service")
        rule.check(importedClasses)
    }

    @ArchTest
    fun exceptionsShouldHaveSimpleNameEndingWithService(importedClasses: JavaClasses?) {
        val rule = ArchRuleDefinition
            .classes()
            .that()
            .resideInAPackage("..exceptions..")
            .should()
            .haveSimpleNameEndingWith("Exception")
        rule.check(importedClasses)
    }
    @ArchTest
    fun entitiesShouldHaveSimpleNameEndingWithEntity(importedClasses: JavaClasses?) {
        val rule = ArchRuleDefinition
            .classes()
            .that()
            .areAnnotatedWith(Entity::class.java)
            .should()
            .haveSimpleNameEndingWith("Entity")
        rule.check(importedClasses)
    }
}
