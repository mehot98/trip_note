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
-- Table structure for table `members`
--

DROP TABLE IF EXISTS `members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `members` (
  `user_id` varchar(16) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `user_password` varchar(16) NOT NULL,
  `email_id` varchar(20) DEFAULT NULL,
  `email_domain` varchar(45) DEFAULT NULL,
  `join_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `token` varchar(1000) DEFAULT NULL,
  `profile_img` mediumblob,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
INSERT INTO `members` VALUES ('admin','관리자','1234','admin','google.com','2023-10-08 07:10:08',NULL,NULL),('hello','헬로싸피','1234','ssafy.kim','ssafy.com','2023-10-26 06:33:08',NULL,NULL),('hissam','안효인','9876','hissam','ssafy.com','2023-10-26 05:52:05',NULL,NULL),('mehot98','이승민','1234','mehot98','naver.com','2023-11-23 06:05:42','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyZWZyZXNoLXRva2VuIiwiaWF0IjoxNzAwNzE5NTgyLCJleHAiOjE3MDMzMTE1ODIsInVzZXJJZCI6Im1laG90OTgifQ.Ak_S-SDDje8HlXYj2H7skVQ3CadufuP8kkEb-OoOfZ4',_binary '�\��\�\0JFIF\0\0\0\0\0\0�\�\0�\0	( \Z%!1!%)+...383-7(-.+\n\n\n\r\Z\Z-------------------------------------7--------+----��\0\0\�\0\�\"\0�\�\0\0\0\0\0\0\0\0\0\0\0\0\0\0�\�\0B\0\0\0\0\0!1AQaq�\"2����B�\�\��3R#$CScd�����\�\��\�\0\Z\0\0\0\0\0\0\0\0\0\0\0\0�\�\0\"\0\0\0\0\0\0\0\0\0!1A\"Qq2�\�\0\0\0?\0b\�)���&ڦ�o<�j���\�i�\�pG@Ցw\�EU,���4\�\�\�B�4��8\���1�I�[�\�ƻ\�\�\�$��\�}\�e\�\Z[7\�x�a�z�|�>(\�\�}\�;^\�+z�S^ХkĒi�?\�nC(�[��7�\'�E�L��-�}�\\U\�Zlaݩ�\�:���\�z)ƶzez�O#6\�EIi�N\�\�8k>]P��d\�b�\�\�\�\�&z8�����6�g�ui�\�|\�[\�$�U�~V�b�q}�\�<^�\�9�\�^�\�4q�%\�Bt*)��\�w]���\'\n\�S�\�u\�2\�/%��.]�zF\�\�M\�{�,�E\�\�hد�B�\�д���WѥZ�Z����\�%[b\��)W}F�\�Ȕ��z�b��h��qL,j&4�5\n��a��JHڴ��ĥ��iW	\�Щl�ۺ\�w�0/nw:e��m�\�\�*m�\��.#*�\�?�m����g�)[;Ꮚ%J�\�\�ĵZ�ya����\0[\�S]^�\�V��:HF�m\\zx�����\�Aw\�籛2\r\nܯW���T�V\�\�Ξ-k<�O\Z�2;\n;��\�̻��Mh\�\�\�<��A�a�\�\�\�\�G!r\��-^��\�*�\�h�\�n��\��5\�:t\�YK\�\�\�7r�$\�\��]\�\n;e?N��	\�\�/Fd���������\�ĸ=|�\�a\��^\�	C��\�\�X���i,I1�3;w>Qp\�\�\\Nk\"v�3��R:�		`=i\�#&\�\��bѣR\� �5�\�\�*\\�\�l.^_Q\�1�^\�O̻�\��\0\"	\�o�Ydҥe�\�x��J+\�$�\n\�\�\�<��\�z.$�\�x�\�ٛ6_�<7b���;�rє��%\�Q\�O�\�v���_b\�rǊ9E\��\rk�d\�jw��\n��\�`r�;/P6p\�\�v5��N?\Z�al,��ΪM\��\�V�E�Տ�+�\�t\�\�#RG���\�8��C\�\�)\��\0Bpv��\�kg�|a\�\Zi�F��b�\�U`�\\��\'�Gȭ\'��h[�w�\�ē=]�-h��=\�+�9\�M�\��7����`A����\�\�\�\�\�V\�Z�x\�0�TY�Zݛ��h^�D�6k0�\�\�\�o�y-K)��3`��d��(;4��\�\Zܯ�7\r}K�_`\��\�|#\�\�,H�ה�/���_�S��@�:�te\�\�\r}[�]<�\�\�je2L\�A���[*�\�폹�Z�ޡhK}\�\rA���jR�O�f�/��6�\�5ZC4ͫG��\��\Z\�s\��(fG����G$�ƽ���丕�{�\�A���^\��\�\�x�\�,44�g�������i\�a����J���i��i\�\�\�\�\�\�;k\���L�FM=q\�\�q�5\���~\�\�q��\�\�\n\�/��x��սC\�X<\�<ն\�\�\����%�����\���5�ݾ\�\�\�}��\�\��lʆAJ\�\�pd��͠�\�i\�d\�\�jF3S�1.�h4,���\0�˱	��[z\�\��T㩽\�\�-2V1������	i\���\�Q�jݧ�\�*\�4x?�\0J�\�	��i��J\Z/�T\��Ԥ�@\��^�%(���ʽ��Ah�_�\�D\�\�v.�U\�\�)[V�\�\� �>�m�!��\�S\�TS\�*#���-\�)\�:\�q�q-iф#�H$+Ao�9[��uY\�\�h\�FXi�6aNYX:	1\�i��.���p�5\�\��6v\�\�\\Ǖ\�\��\��ď]>\�/��\�d7S�ӧ�5\�\�\Z�<(\�>Y+����~;j܏\�\�}\�E`�JF��V>�[I/���\�k��wt\�~�X��Y˙��ճ\��1k�!c\�m\�hS�\�	���\����SC)�\� ��\�4n��ZF�os\�A�R	Q\r�u?��m\�Q��6mV�>9\�С]�?\�k5sBt����l3Գk�e`�\��Vv�T�59\�\�JNO���1�ײ\�o��I=Z[z��[�\�&�\�Jƴ\"\�y�o\\v</�M\��R�|�\�\�Pxi�\��g�T�[a\Z�\���ʄ��\�?L\�_؈b��y~㣕\�\�\�R7\�LZ�6:�gO\'?��y��U^ϔ\�\�\ZsQqso��\'��\��[d\n�-��jˇW�\�ڬ��\�9q����&\�\�I�\��A�A#�dy\�E�<3�R�o(��\�w�C�ڠIS�\�\�W�ΣY\�Vs�\�m�\�	�\�\�9Zr��祩l�����Z��r�b�(7OW/�^L^#��0<�X�+qg����F-�B\�f\�t,V\�3z+D\r�\�\�N\�D%4\"(t:\�m\\\�Q!�F+\�@�!1�\n\�M;IhbS\�Vi[T�\�N,\�)���+V:t)t\�)au�3�\�9J8\�ͻ�\\\�K�\�\n�ZQ\�|��$\�R�\�\�4\��^�yw\�\�O�Z{�r��1Ϯ;�c\�\�!%L\�XO\0d\r\r\�Y,/NAy̙�\�`=\�D�\�P�3\�W\�\�ҵx\�٤�\�\\/�׫RJt%\'����F�~7*1�T\�O���\�\�\�\����4\�\�e��\���^1�B\�\�M3�J�=\�\'žw\�\�U\��u�\�͎�g7�\��\�дFin���4�R�Z���E��\�\�&]��� q�\��1B�ُ6\�\�n\�=@\�y-Jm�\�N�ڴ������[�\�A\�\\1�]I;X�NL�77q;�)AT���\�\�F\"\�zժB\�ѩ�I�jO\�x�Z\'\�(:gj\�\�h�\�\�Jlՠ�(d^��z꼋{�N�)j\�@���ɧmmG��f5>3�\r~\'N>���IH׍%�\�S���S/��D\�,g�I9�~�\�\���\�Tl\�\�;��!�;�X��[�����r\n�\�\�u��0���$1:`]\n\�\r\��\���L6d(jpX9VcTa��\�\�1�Hz\�BU#�0��/��QK%�r\'B�U��F2���\�\�#��/�/��=J\�\�S�f��O\�l9&�\�ȡn�\�d\�^M\�s�ȍ�:�Y\�c���^c\�h�Su�*GT�c��E\�<<<\"���ӏ�Z�OF��r2f��\�\n\\�1\�\��\0$2I{5u7�Ǣ�57a	.杪gD$iN&����\�zi�\�G@��V�h\�\�=X+�d�\�;���\�7:�*�[v\�F#4z!���cz�\��:; \�\�OD�aiD2E)�lx�Q9���]��!�Cf9�u�#Rx@�Ȟ=�i�\�y9\�\n\�YА\�1���(���=6V�� \�E	�7�\�\�i�\���!z��\�j���3�A94fh��x2�r7 H\���\�t\�;o�N��\��M��U%�\�3\�\�G�;<z\�\�/%�\'&b�\�w##�=Ke\�\�B	\�A轎DPݷ��\\�\�ށ$ΤI�ҘT)FC1 $�cU6A��p�!3Y\�\�\��PΩ-F�\�q\�&�6]D^�X\� \�]\r!�R+\�\�\�j�\r\�-N:��t\rB \n[�/Q\�=X��\�d_f{�\�\��h��\�pe�M�r�\�Z	���#F\�O��ic�djޚ^1#&^�V�`\�\�W\�\��Sx\�˹ӂU-��\�\��38�7��!\�OQ\�\�zN	\�!qF2�w�-,�ZG:�\�ه��7=\rNJ_.�u^(>\�47n�;9��ft.��\�\�v\"Gi1\�)فh5Y\�n�\�R�\�;�\'�\"�\�\�_\�y�\�}}G\�E~\���R\���C4&Z.\�sN�Cj�3�\�\'1UjUKQ���n\�\r��$jB��!T��\"xF}�\�Ϙ8a\�g��R\�s\�*�/NL\�g��h-V-RG�\'l�R�͔l\n�л��l=�v��1\�q��QBI�\�XT�FeT\�]�\�a\�C�YҭZ�\�\��q�e]\�\�\Z��\�^�W/\�k\�\�/���:�U~�\0il�\�e\�l�B�)\�h�_��ݜU�#�m�1���\�y\��4!7�)騒\�ٗo%��\�\�`�Zv~\'i<R\r�\��Z�س�M��Xy\�w9\Z\�\�٣�&^�[\�\���ª\��yi��YZ=W�\'��{�p\�\����>Q\�\Z�����gV4y\���}�G�\�\��\�9QD���\�$�d�Mi�\�Hӌ���W��92Y|p���|�	\\�\��g<\�9R	�\���\�ݗC3�d��)R�LUxg}|\�\�za=�o�7�,�\�$)^\�y\�2\���\'������\�Ex\�\�;�3Ҟ~V5Nݽp\�\�Oi?P\�\'�B�$����k@�*,<��\��h�9�m���*����Z�KT�u\�\��\���S]��;ϗ����+ef��s0�\�\��|\�JQK/�3�\��;�L$�\nF^ ��Jћa�PzU5݇u]š��s\��F\0g/R��� 1�5\':f�\�ʩ�e�P�s�����f�ʼ%\'\�\�\�AM\��O\��n1\�\�\�Xo8\�\Z�V\�\�k\�4U�\�髡�\0\��\0�G�\0/���W�K�y� \���egQ����+�6�\����ZB\Z\�6u\��B�42ĉB�)�E/2�*j\nU59�6V��Z�Z���<T\�Y��*\�}\0sMx\�jI�u:�j���YO�@�\�\�(���P\�p$�熰�A�]N�\�졌1�Nf�B�_F��i���\�F/NK~�\0r\�_��2�M	�[sv\�\��\�\�3�\�|�2�\�\�/?�lZ~B�݉Mc����\�V0~Ȟu\�0豒\'�L��G���R\�\n-G�A1�өYi�\�\�ԴS\�ƫ\��u\�U�K)y$����\�\�}\n�\�\�\�7��\�\�\rz݆\�d����\0��.\�^�QZ�z�3\n\�.�!\0���[dB\nf/\"�\�B\�i\�H�!c��u� Q�\�g\�Zc7�N�Ȅ1�C�~�!\��O��(\��\�AFA\�\�\�\�\�Ԅ=�\n�!�Q�Ԅ2)n\n�\�3�!1�\�'),('ss3823','오상훈','asdfasdf','ss38230119','gmail.com','2023-11-21 06:39:59','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyZWZyZXNoLXRva2VuIiwiaWF0IjoxNzAwNTQ4ODEwLCJleHAiOjE3MDMxNDA4MTAsInVzZXJJZCI6InNzMzgyMyJ9.Tgl54_aeytAxalS6PMNCDfD3kX5J67q2o0d_Ppxeuns',NULL);
INSERT INTO `members` VALUES ('ssafy.hong','홍싸피','1234','ssafy.hong','ssafy.com','2023-10-26 06:37:12',NULL,NULL),('ssafy.kim','김싸피','1234','ssafy.kim','ssafy.com','2023-10-26 06:33:08',NULL,NULL),('ssafy1','ssafy1','1234','ssafy1','ssafy1','2023-11-21 00:13:08','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyZWZyZXNoLXRva2VuIiwiaWF0IjoxNzAwNzAxNjM4LCJleHAiOjE3MDMyOTM2MzgsInVzZXJJZCI6InNzYWZ5MSJ9.UcwuW_PuHJKN2hDswEJGBfsZLcGz8eSyU8p9s_QEuMs',NULL);
/*!40000 ALTER TABLE `members` ENABLE KEYS */;
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