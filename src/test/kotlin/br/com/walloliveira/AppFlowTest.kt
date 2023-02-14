package br.com.walloliveira

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition
import com.tngtech.archunit.library.Architectures
import javax.persistence.Entity

@AnalyzeClasses(packages = ["br.com.walloliveira"])
class AppFlowTest {

    internal enum class Layer(val layerName: String, val layerPackage: String) {
        DOMAINS("Domains", "..domains.."),
        RESOURCES("Resources", "..resources.."),
        SERVICES(
            "Services",
            "..services..",
        ),
        ENTITIES("Entities", "..entities.."),
        REPOSITORIES("Repositories", "..repositories.."),
    }

    @ArchTest
    fun layersShouldHaveAccessOnlyToWhatTheyNeed(importedClasses: JavaClasses?) {
        val layersRule = Architectures.layeredArchitecture()
            .consideringOnlyDependenciesInLayers()
            .layer(Layer.RESOURCES.layerName).definedBy(Layer.RESOURCES.layerPackage)
            .layer(Layer.SERVICES.layerName).definedBy(Layer.SERVICES.layerPackage)
            .layer(Layer.REPOSITORIES.layerName).definedBy(Layer.REPOSITORIES.layerPackage)
            .layer(Layer.DOMAINS.layerName).definedBy(Layer.DOMAINS.layerPackage)
            .layer(Layer.ENTITIES.layerName).definedBy(Layer.ENTITIES.layerPackage)

            .whereLayer(Layer.RESOURCES.layerName).mayOnlyAccessLayers(
                Layer.SERVICES.layerName,
                Layer.DOMAINS.layerName,
            )
            .whereLayer(Layer.SERVICES.layerName).mayOnlyAccessLayers(
                Layer.DOMAINS.layerName,
                Layer.REPOSITORIES.layerName
            )
            .whereLayer(Layer.REPOSITORIES.layerName).mayOnlyAccessLayers(
                Layer.ENTITIES.layerName,
                Layer.DOMAINS.layerName
            )
            .whereLayer(Layer.ENTITIES.layerName).mayOnlyAccessLayers(Layer.DOMAINS.layerName)
            .whereLayer(Layer.DOMAINS.layerName).mayNotAccessAnyLayer()
        layersRule.check(importedClasses)
    }

    @ArchTest
    fun servicesShouldNotDependOnOtherServices(importedClasses: JavaClasses?) {
        val rule = ArchRuleDefinition
            .noClasses()
            .that()
            .resideInAPackage("..services..")
            .should()
            .dependOnClassesThat()
            .resideInAPackage("..services..")
        rule.check(importedClasses)
    }

    @ArchTest
    fun resourcesShouldHaveSimpleNameEndingWithResource(importedClasses: JavaClasses?) {
        val rule = ArchRuleDefinition
            .classes()
            .that()
            .resideInAPackage("..resources..")
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
