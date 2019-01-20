package com.eric.karschner.pcbuilder


abstract class PCPart(val price: Int, val name: String, val brand: String, val link: String, val type: String)

class Gpu(price: Int, name: String, brand: String, link: String, type: String) : PCPart(price, name, brand, link, type)

class Cpu(price: Int, name: String, brand: String, link: String, type: String, val motherboard: Motherboard) : PCPart(price, name, brand, link, type)

class Monitor(price: Int, name: String, brand: String, link: String, type: String) : PCPart(price, name, brand, link, type)

class Storage(price: Int, name: String, brand: String, link: String, type: String, val storage: Int) : PCPart(price, name, brand, link, type)

class Motherboard(price: Int, name: String, brand: String, link: String, type: String) : PCPart(price, name, brand, link, type)

class Ram(price: Int, name: String, brand: String, link: String, type: String) : PCPart(price, name, brand, link, type)

class Psu(price: Int, name: String, brand: String, link: String, type: String) : PCPart(price, name, brand, link, type)

class Case(price: Int, name: String, brand: String, link: String, type: String) : PCPart(price, name, brand, link, type)

val microATXAM4 = Motherboard(89, "Asus - PRIME B450M-A/CSM", "Asus", "https://pcpartpicker.com/product/wW66Mp/asus-prime-b450m-acsm-micro-atx-am4-motherboard-prime-b450m-acsm", "Motherboard")
val microATXLGA1151 = Motherboard(70, "Gigabyte B360M DS3H", "Gigabyte", "https://www.outletpc.com/ki5130.html?utm_source=ki5130&utm_medium=shopping%2Bengine", "Motherboard")

val RX580 = Gpu(253, "MSI RX 580 ARMOR 8G OC", "AMD", "https://www.amazon.com/dp/B06XZQMMHJ/", "GPU")
val RTX2070 = Gpu(515, "MSI GeForce RTX 2070 ARMOR 8G", "NVIDIA", "https://www.newegg.com/Product/Product.aspx?Item=N82E16814137366", "GPU")
val GTX1050Ti = Gpu(187, "Asus GTX 1050 TI-O4G-LP-BRK", "NVIDIA", "https://www.bhphotovideo.com/c/product/1431200-REG/asus_gtx1050ti_o4g_lp_brk_graphics_card.html", "GPU")

val Ryzen2600X = Cpu(200,"AMD - Ryzen 5 2600X","AMD","https://www.amazon.com/dp/B07B428V2L", "CPU", microATXAM4)
val i78700K = Cpu(370,"Intel Core i7-8700K", "Intel", "https://www.amazon.com/dp/B07598VZR8", "CPU", microATXLGA1151)
val i38100 = Cpu(119, "Intel Core i3-8100", "Intel", "https://www.amazon.com/dp/B0759FTRZL/", "CPU", microATXLGA1151)

val HDD160 = Storage(18, "Western Digital - Caviar Blue 160 GB", "Western Digital", "https://pcpartpicker.com/product/bBR48d/western-digital-internal-hard-drive-wd1600aajs", "Storage", 160)
val SSD240 = Storage(33, "Kingston - A400 240 GB", "Kingston", "https://pcpartpicker.com/product/btDzK8/kingston-a400-240gb-25-solid-state-drive-sa400s37240g", "Storage", 240)
val HDD1000 = Storage(59, "Seagate - Barracuda 1 TB", "Seagate", "https://pcpartpicker.com/product/kLmLrH/seagate-internal-hard-drive-st31000524as", "Storage",1000)

val DDR48GB = Ram(50, "G.SKILL Aegis 8GB 288-Pin DDR4 SDRAM", "Aegis", "https://www.newegg.com/Product/Product.aspx?Item=N82E16820232419&Description=ddr4%20ram&cm_re=ddr4_ram-_-20-232-419-_-Product", "RAM")

val EVGA500W = Psu(44, "EVGA 500 B1, 80+ BRONZE 500W", "EVGA", "https://www.amazon.com/EVGA-BRONZE-Warranty-Tester-100-B1-0600-KR/dp/B00DZ6R9GE?th=1", "PSU")

val DIYPCCase= Case(24, "DIYPC MA08-BK Mini-Tower", "DIYPC", "https://www.newegg.com/Product/Product.aspx?Item=N82E16811353049", "Case")
