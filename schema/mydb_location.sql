-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location` (
  `location_id` int NOT NULL,
  `x` double NOT NULL,
  `y` double NOT NULL,
  `place_url` text NOT NULL,
  `place_name` varchar(30) NOT NULL,
  `address_name` text NOT NULL,
  PRIMARY KEY (`location_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (7913306,129.159854668484,35.1585232170784,'http://place.map.kakao.com/7913306','해운대해수욕장','부산 해운대구 우동'),(8130712,127.38808330058887,36.36914933740887,'http://place.map.kakao.com/8130712','한밭수목원','대전 서구 만년동 396'),(8202423,129.118976093583,35.1531932736837,'http://place.map.kakao.com/8202423','광안리해수욕장','부산 수영구 광안동 192-20'),(8536416,127.144388934126,35.8552035317834,'http://place.map.kakao.com/8536416','전주동물원','전북 전주시 덕진구 덕진동1가 73-48'),(10731896,127.152557091072,35.8148243087693,'http://place.map.kakao.com/10731896','전주한옥마을','전북 전주시 완산구 풍남동3가 64-1'),(11327879,127.38515156992952,36.33364082775771,'http://place.map.kakao.com/11327879','삼성래미안아파트','대전 서구 가장동 55'),(11505186,127.397754143625,36.2893534027601,'http://place.map.kakao.com/11505186','대전오월드','대전 중구 사정동 117-1'),(13115302,127.47192694194881,36.23392573520695,'http://place.map.kakao.com/13115302','상소동산림욕장','대전 동구 상소동 산 1-1'),(16779100,127.1332005894921,35.838493649647525,'http://place.map.kakao.com/16779100','금암소바','전북 전주시 덕진구 금암동 729-6'),(22875776,129.04528136992104,35.077914827298564,'http://place.map.kakao.com/22875776','흰여울문화마을','부산 영도구 영선동4가 1044-6'),(24923795,127.42823219617,36.3283701459362,'http://place.map.kakao.com/24923795','으능정이스카이로드','대전 중구 은행동'),(188403018,129.17281473802996,35.15815259880406,'http://place.map.kakao.com/188403018','해운대블루라인파크 미포정거장','부산 해운대구 중동 1019-2'),(219599099,127.29789387736615,36.35018167153658,'http://place.map.kakao.com/219599099','피에스타PC방 대전한밭대점','대전 유성구 덕명동 587-3'),(404134034,129.21918520614517,35.194145326002456,'http://place.map.kakao.com/404134034','스카이라인루지 부산','부산 기장군 기장읍 시랑리 산 46'),(2058456587,129.171515615043,35.2870431653779,'http://place.map.kakao.com/2058456587','아홉산숲','부산 기장군 철마면 웅천리 26');
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-23 16:46:28
