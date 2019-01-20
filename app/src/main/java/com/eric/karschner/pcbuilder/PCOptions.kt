package com.eric.karschner.pcbuilder

data class PCOption(val gpu: Gpu?, val cpu: Cpu, val storage: Storage, val motherboard: Motherboard, val ram: Ram, val psu: Psu, val case: Case = DIYPCCase)

val officeMediumStorage = PCOption(null, i38100, SSD240, i38100.motherboard, DDR48GB, EVGA500W)
val officeHighStorage = PCOption(null, i38100, HDD1000, i38100.motherboard, DDR48GB, EVGA500W)

val gamingLowBudget = PCOption(GTX1050Ti, i38100, HDD160, i38100.motherboard, DDR48GB, EVGA500W)
val gamingMediumBudget = PCOption(RX580, Ryzen2600X, SSD240, Ryzen2600X.motherboard, DDR48GB, EVGA500W)
val gamingHighBudget = PCOption(RTX2070, i78700K, HDD1000, i78700K.motherboard, DDR48GB, EVGA500W)

val videoEditingMediumStorage = PCOption(RX580, Ryzen2600X, SSD240, Ryzen2600X.motherboard, DDR48GB, EVGA500W)
val videoEditingHighStorage = PCOption(RX580, Ryzen2600X, HDD1000, Ryzen2600X.motherboard, DDR48GB, EVGA500W)

val PCOptions = arrayListOf(officeMediumStorage, officeHighStorage, gamingLowBudget, gamingMediumBudget, gamingHighBudget, videoEditingMediumStorage, videoEditingHighStorage)