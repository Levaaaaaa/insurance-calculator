workspace "Name" "Description" {

    !identifiers hierarchical

    model {
        u = person "User"
        ss = softwareSystem "Software System" {
            wa = container "Web Application" {
                calcServ = component "Calculate Premium Service"

                group val {
                    mainVal = component "TravelAgreementValidator"
                    agrVal = component "TravelOnlyAgreementValidator"
                    agrFieldVal = component "TravelAgreementFieldValidation" "list of validation for agreement fields"
                    persFieldVal = component "TravelPersonFieldValidation" "list of validation for person fields"
                    persVal = component "TravelOnlyPersonValidator"
                }

                group "underwriting" {
                    underw = component "TravelUnderwriting" "calculate premium"
                    calc = component "SelectedRisksPremiumCalculator" "calculate premium for each risks and sum"
                    riskCalc = component "TravelRiskPremiumCalculator" "list of calculators, calculate premium each for own risk"
                }

                group "saveUtil" {
                    argSave = component "AgreementSaveUtil" "prepare agreement to saving"

                    mapAgr = component "AgreementEntityService" "mapping agreement into entity and save"
                    mapPers = component "PersonEntityService" "mapping agreement into entity and save"
                    mapRiskInAgr = component "AgreementRiskEntityService" "mapping risk-agreement relationship into entity and save"
                    mapPersInAgr = component "AgreementPersonEntityService" "mapping person-agreement relationship into entity and save"
                    mapRiskInPers = component "PersonRiskEntityService" "mapping risk-person relationship into entity and save"
                }

                group "getService" {
                    getServ = component "Get Agreement Service"
                    getRepo = component "GetAgreementRepository"
                    buildAgr = component "BuildAgreementService"
                }

                group "repos" {
                    agrRepo = component "AgreementRepository"
                    persRepo = component "PersonRepository"
                    riskRepo = component "AgreementRiskEntityRepository"
                    agrPersRepo = component "AgreementPersonEntityRepository"
                    riskPersRepo = component "PersonRiskEntityRepository"
                }
            }
            db = container "Database Schema" {
                tags "Database"
            }
        }

        u -> ss.wa "Uses"
        u -> ss.wa.calcServ "calculatePremium"


        ss.wa.calcServ -> ss.wa.mainVal "validate"

        ss.wa.mainVal -> ss.wa.agrVal "validate"
        ss.wa.agrVal -> ss.wa.agrFieldVal "validate"

        ss.wa.mainVal -> ss.wa.persVal "validate"
        ss.wa.persVal -> ss.wa.persFieldVal "validate"



        ss.wa.calcServ -> ss.wa.underw "calculatePremium"
        ss.wa.underw -> ss.wa.calc "calculatePremiumForAllRisks"
        ss.wa.calc -> ss.wa.riskCalc "calculatePremium"
        ss.wa.calc -> ss.wa.riskCalc "getIc"



        ss.wa.calcServ -> ss.wa.argSave "saveAgreement"

        ss.wa.argSave -> ss.wa.mapAgr "saveAgreement"
        ss.wa.mapAgr -> ss.wa.agrRepo "save, findBy"

        ss.wa.argSave -> ss.wa.mapPers "getPersonEntity"
        ss.wa.mapPers -> ss.wa.persRepo "findBy"
        ss.wa.mapPers -> ss.wa.persRepo "save"

        ss.wa.argSave -> ss.wa.mapRiskInAgr "saveRisk"
        ss.wa.mapRiskInAgr -> ss.wa.riskRepo "findByIcAndAgreement"
        ss.wa.mapRiskInAgr -> ss.wa.riskRepo "save"

        ss.wa.argSave -> ss.wa.mapPersInAgr "savePerson"
        ss.wa.mapPersInAgr -> ss.wa.agrPersRepo "findByName"
        ss.wa.mapPersInAgr -> ss.wa.agrPersRepo "save"

        ss.wa.argSave -> ss.wa.mapRiskInPers "savePersonRisk"
        ss.wa.mapRiskInPers -> ss.wa.riskPersRepo "findByIcAndPerson"
        ss.wa.mapRiskInPers -> ss.wa.riskPersRepo "save"

        ss.wa.agrRepo -> ss.db "Save agreement"


        u -> ss.wa.getServ "Send UUID of agreement"
        ss.wa.getServ -> ss.wa.getRepo "findByUUID"
        ss.wa.getRepo -> ss.db "read agreement"

        ss.wa.getServ -> ss.wa.buildAgr "build agreement"
        ss.wa.buildAgr -> ss.wa.riskRepo "findByIcAndAgreement"
        ss.wa.riskRepo -> ss.db "find risks"

        ss.wa.buildAgr -> ss.wa.agrPersRepo "findByAgreement"
        ss.wa.agrPersRepo -> ss.db "find person with agreement"


        ss.wa -> ss.db "Reads from and writes to"
    }
    views {
        systemContext ss "Diagram1" {
            include *
            autolayout lr
        }

        container ss "Diagram2" {
            include *
            autolayout lr
        }

        component ss.wa "Diagram3" {
            include *
            autolayout lr
        }

        styles {
            element "Element" {
                color #ffffff
            }
            element "Person" {
                background #d34407
                shape person
            }
            element "Software System" {
                background #f86628
            }
            element "Container" {
                background #f88728
            }
            element "Component" {
                background #d34407
            }
            element "Database" {
                shape cylinder
            }
        }
    }

    configuration {
        scope softwaresystem
    }
}