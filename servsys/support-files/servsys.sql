-- MySQL dump 10.9
--
-- Host: localhost    Database: memco
-- ------------------------------------------------------
-- Server version	4.1.10a-max-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

--
-- Table structure for table `ab_instrument`
--

DROP TABLE IF EXISTS `ab_instrument`;
CREATE TABLE `ab_instrument` (
  `abenum` int(11) NOT NULL auto_increment,
  `abins` text NOT NULL,
  `abman` text,
  `abmodel` text,
  `abserial` text,
  `abcal` date default NULL,
  `abdesc` text,
  PRIMARY KEY  (`abenum`),
  KEY `abenum` (`abenum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `callcompl`
--

DROP TABLE IF EXISTS `callcompl`;
CREATE TABLE `callcompl` (
  `transnum` int(11) NOT NULL auto_increment,
  `codenum` int(11) NOT NULL default '0',
  `callslip` text NOT NULL,
  KEY `transnum` (`transnum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `callslip`
--

DROP TABLE IF EXISTS `callslip`;
CREATE TABLE `callslip` (
  `crecnum` int(11) NOT NULL auto_increment,
  `custnum` int(11) NOT NULL default '0',
  `custsite` text,
  `sitenum` text,
  `callslip` text NOT NULL,
  `cdate` date NOT NULL default '2000-01-01',
  `equip1` int(11) default NULL,
  `equip2` int(11) default NULL,
  `equip3` text,
  `equip4` text,
  `reason` text,
  `services` text,
  `recommendations` text,
  `rscheduled` text,
  `charges` decimal(10,2) default NULL,
  `collected` decimal(10,2) default NULL,
  `notes` text,
  `followup` tinyint(4) NOT NULL default '0',
  `crectype` text,
  `techid` text,
  `servsync` int(11) default NULL,
  PRIMARY KEY  (`crecnum`),
  UNIQUE KEY `crecnum` (`crecnum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `checkme`
--

DROP TABLE IF EXISTS `checkme`;
CREATE TABLE `checkme` (
  `crecnum` int(11) NOT NULL auto_increment,
  `custnum` int(11) NOT NULL default '0',
  `custsite` text,
  `sitenum` text,
  `callslip` text NOT NULL,
  `cdate` date NOT NULL default '2000-01-01',
  `equip1` int(11) default NULL,
  `equip2` int(11) default NULL,
  `equip3` text,
  `equip4` text,
  `reason` text,
  `services` text,
  `recommendations` text,
  `csp` text,
  `newins` text,
  `ttype` text,
  `actype` text,
  `manyear` text,
  `min1` text,
  `min2` text,
  `rtype` text,
  `trueflow` text,
  `hport` text,
  `mdevice` text,
  `targetas` text,
  `oa1` text,
  `oa2` text,
  `rwb1` text,
  `rwb2` text,
  `rdb1` text,
  `rdb2` text,
  `sdb1` text,
  `sdb2` text,
  `slt1` text,
  `slt2` text,
  `est1` text,
  `est2` text,
  `cst1` text,
  `cst2` text,
  `llt1` text,
  `llt2` text,
  `lp1` text,
  `lp2` text,
  `hp1` text,
  `hp2` text,
  `ss1` text,
  `ss2` text,
  `rs1` text,
  `rs2` text,
  `rfc1` text,
  `rca1` text,
  `acsuite` text,
  `acn` text,
  `comp` text,
  `afcor` text,
  `rscheduled` text,
  `charges` decimal(10,2) default NULL,
  `collected` decimal(10,2) default NULL,
  `notes` text,
  `followup` tinyint(4) NOT NULL default '0',
  `crectype` text,
  `techid` text,
  PRIMARY KEY  (`crecnum`),
  UNIQUE KEY `crecnum` (`crecnum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `compl_cat`
--

DROP TABLE IF EXISTS `compl_cat`;
CREATE TABLE `compl_cat` (
  `catnum` int(11) NOT NULL auto_increment,
  `category` text NOT NULL,
  PRIMARY KEY  (`catnum`),
  KEY `catnum` (`catnum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `compl_codes`
--

DROP TABLE IF EXISTS `compl_codes`;
CREATE TABLE `compl_codes` (
  `codenum` int(11) NOT NULL auto_increment,
  `catnum` int(11) NOT NULL default '0',
  `complcode` text NOT NULL,
  `compltext` text NOT NULL,
  PRIMARY KEY  (`codenum`),
  KEY `codenum` (`codenum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `configcompany`
--

DROP TABLE IF EXISTS `configcompany`;
CREATE TABLE `configcompany` (
  `image` text,
  `imagewidth` text,
  `imagehight` text,
  `coname` text,
  `cologo` text,
  `coaddress` text,
  `cophone` text,
  `useletterhead` text NOT NULL,
  `yearenddate` date default NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `configemail`
--

DROP TABLE IF EXISTS `configemail`;
CREATE TABLE `configemail` (
  `username` text,
  `tech_email` text NOT NULL,
  `time_email` text NOT NULL,
  `svc_email` text NOT NULL,
  `ins_email` text NOT NULL,
  `smtp_server` text NOT NULL,
  `prop_email` text NOT NULL,
  `stock_email` text NOT NULL,
  `semail_cat` text NOT NULL,
  `smtp_user` text,
  `smtp_password` text
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `custnotes`
--

DROP TABLE IF EXISTS `custnotes`;
CREATE TABLE `custnotes` (
  `notenum` int(11) NOT NULL auto_increment,
  `custnum` int(11) NOT NULL default '0',
  `notedate` date default '2000-01-01',
  `notesubj` text,
  `notedata` text,
  PRIMARY KEY  (`notenum`),
  KEY `notenum` (`notenum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers` (
  `custnum` int(11) NOT NULL auto_increment,
  `cname` text NOT NULL,
  `address1` text NOT NULL,
  `address2` text,
  `city` text NOT NULL,
  `state` text NOT NULL,
  `zip` text NOT NULL,
  `homephone` text,
  `altphone` text,
  `cust_notes` text,
  `cemail` text NOT NULL,
  `custsite` text NOT NULL,
  `sitenum` text NOT NULL,
  `custtype` text NOT NULL,
  `servsync` int(11) default NULL,
  UNIQUE KEY `custnum` (`custnum`),
  KEY `custnum_2` (`custnum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `custsurvey`
--

DROP TABLE IF EXISTS `custsurvey`;
CREATE TABLE `custsurvey` (
  `recnum` tinyint(4) NOT NULL auto_increment,
  `custnum` int(11) NOT NULL default '0',
  `gpipingsize` text NOT NULL,
  `dwaterfeedsize` text NOT NULL,
  `dwatersupplysize` text NOT NULL,
  `bwatersupplysize` text NOT NULL,
  `bwaterreturnsize` text NOT NULL,
  `supplymanifold` text NOT NULL,
  `returnmanifold` text NOT NULL,
  `numzones` text NOT NULL,
  `zonecontrol` text NOT NULL,
  `nfloors` text NOT NULL,
  `olength` text NOT NULL,
  `owidth` text NOT NULL,
  `oheight` text NOT NULL,
  `buse` text NOT NULL,
  `bage` text NOT NULL,
  `bventalation` text NOT NULL,
  `bhumidification` text NOT NULL,
  `bcontrols` text NOT NULL,
  `ecurrentvoltage` text NOT NULL,
  `ecurrentphase` text NOT NULL,
  `evoltageneeded` text NOT NULL,
  `ephaseneeded` text NOT NULL,
  `epanbrand` text NOT NULL,
  `epanelroom` text NOT NULL,
  `epanlocation` text NOT NULL,
  `eaddwork` text NOT NULL,
  `fueltype` text NOT NULL,
  `furntype` text NOT NULL,
  `windowtype` text NOT NULL,
  `glasstype` text NOT NULL,
  `stormwindows` text NOT NULL,
  `doortype` text NOT NULL,
  `infiltration` text NOT NULL,
  `sdate` date NOT NULL default '2000-01-01',
  PRIMARY KEY  (`recnum`),
  KEY `recnum` (`recnum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `dbserver`
--

DROP TABLE IF EXISTS `dbserver`;
CREATE TABLE `dbserver` (
  `servername` text,
  `username` text,
  `password` text,
  `dbname` text,
  `mainservername` text NOT NULL,
  `mainusername` text NOT NULL,
  `mainpassword` text NOT NULL,
  `maindbname` text NOT NULL,
  `thismainserver` text
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `doc_cat`
--

DROP TABLE IF EXISTS `doc_cat`;
CREATE TABLE `doc_cat` (
  `catnum` int(11) NOT NULL auto_increment,
  `category` text NOT NULL,
  PRIMARY KEY  (`catnum`),
  KEY `catnum` (`catnum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `documentation`
--

DROP TABLE IF EXISTS `documentation`;
CREATE TABLE `documentation` (
  `docid` int(11) NOT NULL auto_increment,
  `doctype` int(11) default NULL,
  `filepath` text NOT NULL,
  `docdate` date default NULL,
  `key1` text,
  `key2` text,
  `key3` text,
  `document` blob,
  `docdesc` text NOT NULL,
  UNIQUE KEY `docid` (`docid`),
  KEY `docid_2` (`docid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `doortype`
--

DROP TABLE IF EXISTS `doortype`;
CREATE TABLE `doortype` (
  `doortype` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `ephase`
--

DROP TABLE IF EXISTS `ephase`;
CREATE TABLE `ephase` (
  `ephase` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `equipment`
--

DROP TABLE IF EXISTS `equipment`;
CREATE TABLE `equipment` (
  `enum` int(11) NOT NULL auto_increment,
  `custnum` int(11) NOT NULL default '0',
  `custsite` text,
  `sitenum` text,
  `brand` text NOT NULL,
  `modelnum` text NOT NULL,
  `serialnum` text NOT NULL,
  `filter` text,
  `notes` text,
  `etype` text,
  `cseer` text NOT NULL,
  `btuout` text NOT NULL,
  `servsync` int(11) default NULL,
  KEY `enum` (`enum`,`custnum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `evoltage`
--

DROP TABLE IF EXISTS `evoltage`;
CREATE TABLE `evoltage` (
  `evoltage` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `expire_date`
--

DROP TABLE IF EXISTS `expire_date`;
CREATE TABLE `expire_date` (
  `expiredate` date NOT NULL default '2000-01-01'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `flat_rate`
--

DROP TABLE IF EXISTS `flat_rate`;
CREATE TABLE `flat_rate` (
  `recnum` int(11) NOT NULL auto_increment,
  `service` text,
  `tm_primary` decimal(10,2) default NULL,
  `keycode` text,
  `jtime` decimal(10,2) default NULL,
  `jcode` text,
  `specitem` int(11) NOT NULL default '0',
  PRIMARY KEY  (`recnum`),
  UNIQUE KEY `recnum` (`recnum`),
  KEY `recnum_2` (`recnum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `flat_rate_date`
--

DROP TABLE IF EXISTS `flat_rate_date`;
CREATE TABLE `flat_rate_date` (
  `dateupdated` date NOT NULL default '2000-01-01'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `flat_rate_table`
--

DROP TABLE IF EXISTS `flat_rate_table`;
CREATE TABLE `flat_rate_table` (
  `code` int(11) NOT NULL auto_increment,
  `part` text,
  `category` text,
  `keycode` text,
  `hours` decimal(10,2) default '0.00',
  `partcost` decimal(10,2) default '0.00',
  `custnotes` text,
  `nodiscount` text,
  `specitem` int(11) default NULL,
  PRIMARY KEY  (`code`),
  UNIQUE KEY `code` (`code`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `flatrateconfig`
--

DROP TABLE IF EXISTS `flatrateconfig`;
CREATE TABLE `flatrateconfig` (
  `labperhour` decimal(10,2) default NULL,
  `psdiscount` decimal(10,2) default NULL,
  `mrdiscount` decimal(10,2) default NULL,
  `commarkup` decimal(10,2) default NULL,
  `salestax` decimal(10,2) default NULL,
  `partmarkup` decimal(10,2) default NULL,
  `sitemlowprice` decimal(10,2) default NULL,
  `sitemhighprice` decimal(10,2) default NULL,
  `sitemhighhours` decimal(10,2) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `formlist`
--

DROP TABLE IF EXISTS `formlist`;
CREATE TABLE `formlist` (
  `formnum` int(11) NOT NULL auto_increment,
  `formname` text,
  `formdescription` text,
  PRIMARY KEY  (`formnum`),
  UNIQUE KEY `formnum` (`formnum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `frconfig`
--

DROP TABLE IF EXISTS `frconfig`;
CREATE TABLE `frconfig` (
  `tmadditional` decimal(10,2) default NULL,
  `psprimary` decimal(10,2) default NULL,
  `psadditional` decimal(10,2) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `fuelpurch`
--

DROP TABLE IF EXISTS `fuelpurch`;
CREATE TABLE `fuelpurch` (
  `grecnum` int(11) NOT NULL auto_increment,
  `pdate` date NOT NULL default '2000-01-01',
  `veichle` text NOT NULL,
  `card` text NOT NULL,
  `odometer` int(11) NOT NULL default '0',
  `gals` decimal(3,1) NOT NULL default '0.0',
  `cost` decimal(4,2) NOT NULL default '0.00',
  UNIQUE KEY `grecnum` (`grecnum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `fueltype`
--

DROP TABLE IF EXISTS `fueltype`;
CREATE TABLE `fueltype` (
  `fueltype` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `furntype`
--

DROP TABLE IF EXISTS `furntype`;
CREATE TABLE `furntype` (
  `furntype` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `glasstype`
--

DROP TABLE IF EXISTS `glasstype`;
CREATE TABLE `glasstype` (
  `glasstype` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `htg_load_cats`
--

DROP TABLE IF EXISTS `htg_load_cats`;
CREATE TABLE `htg_load_cats` (
  `cat_num` int(11) NOT NULL auto_increment,
  `cat_order` int(11) NOT NULL default '0',
  `cat_desc` text NOT NULL,
  `unit_of_m` text NOT NULL,
  PRIMARY KEY  (`cat_num`),
  UNIQUE KEY `cat_num_2` (`cat_num`),
  KEY `cat_num` (`cat_num`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `htg_load_mult`
--

DROP TABLE IF EXISTS `htg_load_mult`;
CREATE TABLE `htg_load_mult` (
  `recnum` int(11) NOT NULL auto_increment,
  `catnum` int(11) NOT NULL default '0',
  `mdesc` text NOT NULL,
  `design_temp` int(11) NOT NULL default '0',
  `ht_trans_mult` double(10,1) NOT NULL default '0.0',
  PRIMARY KEY  (`recnum`),
  UNIQUE KEY `recnum_2` (`recnum`),
  KEY `recnum` (`recnum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `infiltration`
--

DROP TABLE IF EXISTS `infiltration`;
CREATE TABLE `infiltration` (
  `factor` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `inspection`
--

DROP TABLE IF EXISTS `inspection`;
CREATE TABLE `inspection` (
  `crecnum` int(11) NOT NULL auto_increment,
  `custnum` int(11) NOT NULL default '0',
  `custsite` text,
  `sitenum` text,
  `callslip` text NOT NULL,
  `idate` date NOT NULL default '2000-01-01',
  `equip1` int(11) default NULL,
  `equip2` int(11) default NULL,
  `equip3` int(11) default NULL,
  `equip4` int(11) default NULL,
  `mbearing` text,
  `mblades` text,
  `ecoil` text,
  `dline` text,
  `dpan` text,
  `ielect` text,
  `mcap` text,
  `hstrips` text,
  `filter` text,
  `gpreassures` text,
  `ignition` text,
  `burners` text,
  `limits` text,
  `flame` text,
  `dinducer` text,
  `humidifier` text,
  `atemp` text,
  `tempsplit` text,
  `crlaa` text,
  `crlar` text,
  `ccapr` text,
  `ccapa` text,
  `frlaa` text,
  `frlar` text,
  `fcapr` text,
  `fcapa` text,
  `fbearing` text,
  `coilcond` text,
  `cleancoil` text,
  `contactor` text,
  `scap` text,
  `ctimedelay` text,
  `oelectrical` text,
  `comppad` text,
  `recommendations` text,
  `services` text,
  `dueamount` decimal(10,2) default NULL,
  `paidamount` decimal(10,2) default NULL,
  `notes` text,
  `lpres` text,
  `hpres` text,
  `startco` text NOT NULL,
  `runco` text NOT NULL,
  `stacktemp` text NOT NULL,
  `ventpipe` text NOT NULL,
  `oleaks` text,
  `ochimney` text,
  `opump` text,
  `ocontrols` text,
  `otstat` text,
  `oprimesafety` text,
  `osafetime` text,
  `oigntrans` text,
  `olubemotors` text,
  `ofulemix` text,
  `onozzle` text,
  `ogross` text,
  `onet` text,
  `osmoke` text,
  `oco2` text,
  `oo2` text,
  `oco` text,
  `oexcessair` text,
  `obreachdraft` text,
  `ofiredraft` text,
  `oeffic` text,
  `orating` text,
  `opower` text,
  `otank` text,
  `otcond` text,
  `odheat` text,
  `ocombustion` text,
  `oelectrodes` text,
  `obrush` text,
  `ofilters` text,
  `followup` tinyint(4) NOT NULL default '0',
  `airflow` text,
  `spres_rated` text,
  `spres_supply` text,
  `spres_return` text,
  `g_filter` text,
  `g_electrical` text,
  `g_looppres` text,
  `g_cleancoil` text,
  `g_cleandrain` text,
  `g_pansensor` text,
  `g_cleancomp` text,
  `g_cleanunit` text,
  `g_oilblower` text,
  `g_cleanpump` text,
  `g_tsplit` text,
  `g_pampr` text,
  `g_pampa` text,
  `g_compar` text,
  `g_compaa` text,
  `g_bampr` text,
  `g_bampa` text,
  `g_pdrop` text,
  `sductsize` text NOT NULL,
  `rductsize` text NOT NULL,
  `liqtemp` text NOT NULL,
  `sucttemp` text NOT NULL,
  `r_temp` text NOT NULL,
  `s_temp` text NOT NULL,
  `rw_temp` text NOT NULL,
  `mcfm` text NOT NULL,
  `out_temp` text NOT NULL,
  `expansion` text,
  `ahage` text,
  `conage` text,
  `techid` text,
  `servsync` int(11) default NULL,
  UNIQUE KEY `crecnum` (`crecnum`),
  KEY `crecnum_2` (`crecnum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `inv_cats`
--

DROP TABLE IF EXISTS `inv_cats`;
CREATE TABLE `inv_cats` (
  `catnum` int(11) NOT NULL auto_increment,
  `category` text NOT NULL,
  `description` text,
  `keyprefix` text,
  PRIMARY KEY  (`catnum`),
  UNIQUE KEY `catnum` (`catnum`),
  KEY `catnum_2` (`catnum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `inv_detail`
--

DROP TABLE IF EXISTS `inv_detail`;
CREATE TABLE `inv_detail` (
  `transnum` int(11) NOT NULL auto_increment,
  `keycode` text,
  `itemnum` int(11) NOT NULL default '0',
  `date` date NOT NULL default '2000-01-01',
  `callslip` text NOT NULL,
  `quantity` decimal(10,2) NOT NULL default '0.00',
  `notes` text,
  UNIQUE KEY `transnum` (`transnum`),
  KEY `transnum_2` (`transnum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `inv_items`
--

DROP TABLE IF EXISTS `inv_items`;
CREATE TABLE `inv_items` (
  `itemnum` int(11) NOT NULL auto_increment,
  `invcatnum` int(11) NOT NULL default '0',
  `itemname` text NOT NULL,
  `description` text,
  `minquant` tinyint(4) default '0',
  UNIQUE KEY `itemnum` (`itemnum`),
  KEY `invcatnum` (`invcatnum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `inv_keycodes`
--

DROP TABLE IF EXISTS `inv_keycodes`;
CREATE TABLE `inv_keycodes` (
  `ID` int(11) NOT NULL auto_increment,
  `keycodep` text,
  `part_number` text,
  `sort_desc` text,
  `description` text,
  `manu` text,
  `location` text,
  `oh_qty` double default NULL,
  `qty_opt` double default NULL,
  `part_cost` double default NULL,
  `extended_cost` double default NULL,
  `sell_price` text,
  `stocknum` text,
  `orderuom` text,
  `conversion` double default NULL,
  `peak_oh` text,
  `off_peak_oh` text,
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `ID` (`ID`),
  KEY `ID_2` (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `inv_keycodes_date`
--

DROP TABLE IF EXISTS `inv_keycodes_date`;
CREATE TABLE `inv_keycodes_date` (
  `dateupdated` date NOT NULL default '2000-01-01'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `inv_use`
--

DROP TABLE IF EXISTS `inv_use`;
CREATE TABLE `inv_use` (
  `recnum` int(11) NOT NULL auto_increment,
  `callslip` text NOT NULL,
  `date` date NOT NULL default '2000-01-01',
  `keycode` text NOT NULL,
  `quant` decimal(10,2) NOT NULL default '1.00',
  `descript` text NOT NULL,
  `notes` text,
  `techid` text,
  `servsync` int(11) default NULL,
  PRIMARY KEY  (`recnum`),
  UNIQUE KEY `recnum` (`recnum`),
  KEY `recnum_2` (`recnum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `jobcomplete`
--

DROP TABLE IF EXISTS `jobcomplete`;
CREATE TABLE `jobcomplete` (
  `recnum` int(11) NOT NULL auto_increment,
  `custnum` int(11) NOT NULL default '0',
  `compdate` date NOT NULL default '2000-01-01',
  `enum1` int(11) default NULL,
  `enum2` int(11) default NULL,
  `enum3` int(11) default NULL,
  `enum4` int(11) default NULL,
  `enum5` int(11) default NULL,
  `enum6` int(11) default NULL,
  `note1` int(11) default NULL,
  `note2` int(11) default NULL,
  `contnum` int(11) default NULL,
  `qualdone` text,
  `startupdone` text,
  `customersat` text,
  `turnoverok` text,
  `jobnum` text,
  PRIMARY KEY  (`recnum`),
  KEY `recnum` (`recnum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `jobs`
--

DROP TABLE IF EXISTS `jobs`;
CREATE TABLE `jobs` (
  `recnum` int(11) NOT NULL auto_increment,
  `cnum` int(11) NOT NULL default '0',
  `jobnum` text NOT NULL,
  `description` text NOT NULL,
  `shophrs` decimal(10,2) NOT NULL default '0.00',
  `fieldhrs` decimal(10,2) NOT NULL default '0.00',
  `elechrs` decimal(10,2) NOT NULL default '0.00',
  `plmhrs` decimal(10,2) NOT NULL default '0.00',
  `salesperson` text NOT NULL,
  `price` decimal(15,2) NOT NULL default '0.00',
  PRIMARY KEY  (`recnum`),
  UNIQUE KEY `recnum_2` (`recnum`),
  KEY `recnum` (`recnum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `jobstatus`
--

DROP TABLE IF EXISTS `jobstatus`;
CREATE TABLE `jobstatus` (
  `recnum` int(11) NOT NULL auto_increment,
  `custnum` int(11) NOT NULL default '0',
  `jobrecnum` int(11) NOT NULL default '0',
  `statusdate` date NOT NULL default '2000-01-01',
  `jobstatus` text NOT NULL,
  `description` text NOT NULL,
  PRIMARY KEY  (`recnum`),
  UNIQUE KEY `recnum_2` (`recnum`),
  KEY `recnum` (`recnum`,`custnum`,`jobrecnum`,`statusdate`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `leads`
--

DROP TABLE IF EXISTS `leads`;
CREATE TABLE `leads` (
  `leadnum` int(11) NOT NULL auto_increment,
  `ncustnum` int(11) NOT NULL default '0',
  `calldate` date NOT NULL default '2000-01-01',
  `name` text NOT NULL,
  `address` text NOT NULL,
  `city` text NOT NULL,
  `state` text NOT NULL,
  `zip` text NOT NULL,
  `custsite` text NOT NULL,
  `phonenumber` text NOT NULL,
  `cellphonenumber` text NOT NULL,
  `workphonenumber` text NOT NULL,
  `contactperson` text NOT NULL,
  `saddress` text NOT NULL,
  `sitenum` text NOT NULL,
  `scity` text NOT NULL,
  `sstate` text NOT NULL,
  `szip` text NOT NULL,
  `salesman` text NOT NULL,
  `aptdate` text NOT NULL,
  `apttime` text NOT NULL,
  `reason` text NOT NULL,
  `refby` text NOT NULL,
  `source` text NOT NULL,
  `building` text NOT NULL,
  `fuel` text NOT NULL,
  `heattype` text NOT NULL,
  `etype1` text NOT NULL,
  `enewrep1` text NOT NULL,
  `enewrep2` text NOT NULL,
  `etype2` text NOT NULL,
  `notes` text NOT NULL,
  `techid` text NOT NULL,
  `email` text NOT NULL,
  `status` text NOT NULL,
  PRIMARY KEY  (`leadnum`),
  UNIQUE KEY `leadnum_2` (`leadnum`),
  KEY `leadnum` (`leadnum`,`ncustnum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `masterworksheet`
--

DROP TABLE IF EXISTS `masterworksheet`;
CREATE TABLE `masterworksheet` (
  `wsrec` int(11) NOT NULL auto_increment,
  `wsdesc` text NOT NULL,
  `wsdate` date NOT NULL default '2000-01-01',
  `wsnotes` text NOT NULL,
  `wsmult` decimal(10,2) NOT NULL default '0.00',
  PRIMARY KEY  (`wsrec`),
  KEY `wsrec` (`wsrec`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `masterwsitem`
--

DROP TABLE IF EXISTS `masterwsitem`;
CREATE TABLE `masterwsitem` (
  `wsrec` int(11) NOT NULL default '0',
  `itemrec` int(11) NOT NULL auto_increment,
  `item` text NOT NULL,
  `keycode` text NOT NULL,
  `quantity` int(11) NOT NULL default '0',
  `cost` decimal(10,2) NOT NULL default '0.00',
  `laborhours` int(11) NOT NULL default '0',
  `laborcost` decimal(10,2) NOT NULL default '0.00',
  `shophours` int(11) NOT NULL default '0',
  UNIQUE KEY `itemrec_2` (`itemrec`),
  KEY `itemrec` (`itemrec`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `package`
--

DROP TABLE IF EXISTS `package`;
CREATE TABLE `package` (
  `recID` int(11) NOT NULL auto_increment,
  `pkgID` text NOT NULL,
  `catID` int(11) NOT NULL default '0',
  `pkgDesc` text NOT NULL,
  `pkgLaborHours` int(11) NOT NULL default '0',
  `pkgPartsCost` double(10,2) NOT NULL default '0.00',
  PRIMARY KEY  (`recID`),
  UNIQUE KEY `recID_2` (`recID`),
  KEY `recID` (`recID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Package Cost for Install';

--
-- Table structure for table `packagecats`
--

DROP TABLE IF EXISTS `packagecats`;
CREATE TABLE `packagecats` (
  `catID` int(11) NOT NULL auto_increment,
  `catDesc` text NOT NULL,
  `adjper` text NOT NULL,
  PRIMARY KEY  (`catID`),
  UNIQUE KEY `catID_2` (`catID`),
  KEY `catID` (`catID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `packages_date`
--

DROP TABLE IF EXISTS `packages_date`;
CREATE TABLE `packages_date` (
  `dateupdated` date NOT NULL default '2000-01-01'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `pagreement`
--

DROP TABLE IF EXISTS `pagreement`;
CREATE TABLE `pagreement` (
  `contnum` int(11) NOT NULL auto_increment,
  `custnum` int(11) NOT NULL default '0',
  `enum1` int(11) default NULL,
  `enum2` int(11) default NULL,
  `enum3` int(11) default NULL,
  `enum4` int(11) default NULL,
  `enum5` int(11) default NULL,
  `enum6` int(11) default NULL,
  `enum7` int(11) default NULL,
  `enum8` int(11) default NULL,
  `enum9` int(11) default NULL,
  `enum10` int(11) default NULL,
  `aservice` text,
  `startdate` date NOT NULL default '2000-01-01',
  `enddate` date NOT NULL default '2000-01-01',
  `term` int(11) NOT NULL default '0',
  `cost` decimal(10,2) NOT NULL default '0.00',
  `notes` text,
  `agrdate` date NOT NULL default '2000-01-01',
  `vperyear` int(11) NOT NULL default '0',
  `visit1` text,
  `visit2` text,
  `visit3` text,
  `visit4` text,
  `visit5` text,
  `visit6` text,
  `servsync` int(11) default NULL,
  `custsite` text,
  `sitenum` text,
  `techid` text,
  PRIMARY KEY  (`contnum`),
  UNIQUE KEY `contnum` (`contnum`),
  KEY `contnum_2` (`contnum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `phone_list`
--

DROP TABLE IF EXISTS `phone_list`;
CREATE TABLE `phone_list` (
  `record_number` int(11) NOT NULL auto_increment,
  `name` varchar(100) NOT NULL default '-',
  `home_number` varchar(100) default NULL,
  `cell_number` varchar(100) default NULL,
  `direct_connect` varchar(100) default NULL,
  `truck_number` varchar(100) default NULL,
  `additional_number` varchar(100) default NULL,
  PRIMARY KEY  (`record_number`,`name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `phone_list_date`
--

DROP TABLE IF EXISTS `phone_list_date`;
CREATE TABLE `phone_list_date` (
  `dateupdated` date NOT NULL default '0000-00-00'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `prevprices`
--

DROP TABLE IF EXISTS `prevprices`;
CREATE TABLE `prevprices` (
  `planrec` int(11) NOT NULL auto_increment,
  `descript` text NOT NULL,
  `tm_est` decimal(10,2) default NULL,
  `yr1` decimal(10,2) default NULL,
  `yr2` decimal(10,2) default NULL,
  `yr3` decimal(10,2) default NULL,
  PRIMARY KEY  (`planrec`),
  UNIQUE KEY `planrec` (`planrec`),
  KEY `planrec_2` (`planrec`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `prevprices_date`
--

DROP TABLE IF EXISTS `prevprices_date`;
CREATE TABLE `prevprices_date` (
  `dateupdated` date NOT NULL default '2000-01-01'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `quote_cat`
--

DROP TABLE IF EXISTS `quote_cat`;
CREATE TABLE `quote_cat` (
  `catnum` int(11) NOT NULL auto_increment,
  `category` text NOT NULL,
  `description` text,
  `adjper` text NOT NULL,
  PRIMARY KEY  (`catnum`),
  UNIQUE KEY `catnum` (`catnum`),
  KEY `catnum_2` (`catnum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `quote_items`
--

DROP TABLE IF EXISTS `quote_items`;
CREATE TABLE `quote_items` (
  `itemnum` int(11) NOT NULL auto_increment,
  `catnum` int(11) NOT NULL default '0',
  `itemname` text NOT NULL,
  `mannum` text,
  `investment` decimal(10,2) default NULL,
  UNIQUE KEY `itemnum` (`itemnum`),
  KEY `invcatnum` (`catnum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `quote_parts`
--

DROP TABLE IF EXISTS `quote_parts`;
CREATE TABLE `quote_parts` (
  `qpartnum` int(11) NOT NULL auto_increment,
  `quotenum` int(11) NOT NULL default '0',
  `itemname` text NOT NULL,
  `mannum` text NOT NULL,
  `itemquant` int(11) NOT NULL default '1',
  `investment` decimal(10,2) NOT NULL default '0.00',
  `custsite` text,
  `sitenum` text,
  `servsync` int(11) default NULL,
  `techid` text,
  PRIMARY KEY  (`qpartnum`),
  KEY `qpartnum` (`qpartnum`,`quotenum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `quoteoptions`
--

DROP TABLE IF EXISTS `quoteoptions`;
CREATE TABLE `quoteoptions` (
  `quotenum` int(11) NOT NULL default '0',
  `quoteoption` int(11) NOT NULL default '0',
  `printorder` int(4) NOT NULL default '0'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `quotes`
--

DROP TABLE IF EXISTS `quotes`;
CREATE TABLE `quotes` (
  `quotenum` int(11) NOT NULL auto_increment,
  `crecnum` int(11) NOT NULL default '0',
  `qdate` date NOT NULL default '2000-01-01',
  `qdescription` text NOT NULL,
  `qpayterms` text,
  `qnotes` text,
  `qadditionalserv` text,
  `status` text NOT NULL,
  `solddate` date NOT NULL default '2000-01-01',
  `jobnum` text NOT NULL,
  `antstart` date NOT NULL default '2000-01-01',
  `qdisc` text NOT NULL,
  `qmult` int(11) NOT NULL default '0',
  `custsite` text,
  `sitenum` text,
  `servsync` int(11) default NULL,
  `techid` text,
  PRIMARY KEY  (`quotenum`),
  KEY `quotenum` (`quotenum`,`crecnum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `quotestatus`
--

DROP TABLE IF EXISTS `quotestatus`;
CREATE TABLE `quotestatus` (
  `status` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `rates`
--

DROP TABLE IF EXISTS `rates`;
CREATE TABLE `rates` (
  `partmult` decimal(10,2) NOT NULL default '0.00',
  `labor` decimal(10,2) NOT NULL default '0.00',
  `totalmult` decimal(10,2) NOT NULL default '0.00'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `resources`
--

DROP TABLE IF EXISTS `resources`;
CREATE TABLE `resources` (
  `recnum` int(11) NOT NULL auto_increment,
  `rname` text NOT NULL,
  `skill` text NOT NULL,
  `leadhelp` text NOT NULL,
  UNIQUE KEY `recnum_2` (`recnum`),
  KEY `recnum` (`recnum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `squote_parts`
--

DROP TABLE IF EXISTS `squote_parts`;
CREATE TABLE `squote_parts` (
  `qpartnum` int(11) NOT NULL auto_increment,
  `quotenum` int(11) NOT NULL default '0',
  `itemname` text NOT NULL,
  `mannum` text NOT NULL,
  `itemquant` int(11) NOT NULL default '1',
  `investment` decimal(10,2) NOT NULL default '0.00',
  PRIMARY KEY  (`qpartnum`),
  KEY `qpartnum` (`qpartnum`,`quotenum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `squotes`
--

DROP TABLE IF EXISTS `squotes`;
CREATE TABLE `squotes` (
  `quotenum` int(11) NOT NULL auto_increment,
  `crecnum` int(11) NOT NULL default '0',
  `qdate` date NOT NULL default '2000-01-01',
  `qdescription` text NOT NULL,
  `qpayterms` text,
  `qnotes` text,
  `qadditionalserv` text,
  `status` text NOT NULL,
  `solddate` date NOT NULL default '2000-01-01',
  `jobnum` text NOT NULL,
  `antstart` date NOT NULL default '2000-01-01',
  `qdisc` text NOT NULL,
  PRIMARY KEY  (`quotenum`),
  KEY `quotenum` (`quotenum`,`crecnum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `startup`
--

DROP TABLE IF EXISTS `startup`;
CREATE TABLE `startup` (
  `crecnum` int(11) NOT NULL auto_increment,
  `custnum` int(11) NOT NULL default '0',
  `callslip` text NOT NULL,
  `idate` date NOT NULL default '2000-01-01',
  `equip1` int(11) default NULL,
  `equip2` int(11) default NULL,
  `mbearing` text,
  `mblades` text,
  `ecoil` text,
  `dline` text,
  `dpan` text,
  `ielect` text,
  `mcap` text,
  `hstrips` text,
  `filter` text,
  `gpreassures` text,
  `ignition` text,
  `burners` text,
  `limits` text,
  `flame` text,
  `dinducer` text,
  `humidifier` text,
  `atemp` text,
  `tempsplit` text,
  `crlaa` text,
  `crlar` text,
  `ccapr` text,
  `ccapa` text,
  `frlaa` text,
  `frlar` text,
  `fcapr` text,
  `fcapa` text,
  `fbearing` text,
  `coilcond` text,
  `cleancoil` text,
  `contactor` text,
  `scap` text,
  `ctimedelay` text,
  `oelectrical` text,
  `comppad` text,
  `recommendations` text,
  `services` text,
  `dueamount` decimal(10,2) default NULL,
  `paidamount` decimal(10,2) default NULL,
  `notes` text,
  `lpres` text,
  `hpres` text,
  `startco` text NOT NULL,
  `runco` text NOT NULL,
  `stacktemp` text NOT NULL,
  `ventpipe` text NOT NULL,
  `oleaks` text,
  `ochimney` text,
  `opump` text,
  `ocontrols` text,
  `otstat` text,
  `oprimesafety` text,
  `osafetime` text,
  `oigntrans` text,
  `olubemotors` text,
  `ofulemix` text,
  `onozzle` text,
  `ogross` text,
  `onet` text,
  `osmoke` text,
  `oco2` text,
  `oo2` text,
  `oco` text,
  `oexcessair` text,
  `obreachdraft` text,
  `ofiredraft` text,
  `oeffic` text,
  `orating` text,
  `opower` text,
  `otank` text,
  `otcond` text,
  `odheat` text,
  `ocombustion` text,
  `oelectrodes` text,
  `obrush` text,
  `ofilters` text,
  `followup` tinyint(4) NOT NULL default '0',
  `airflow` text,
  `spres_rated` text,
  `spres_supply` text,
  `spres_return` text,
  `g_filter` text,
  `g_electrical` text,
  `g_looppres` text,
  `g_cleancoil` text,
  `g_cleandrain` text,
  `g_pansensor` text,
  `g_cleancomp` text,
  `g_cleanunit` text,
  `g_oilblower` text,
  `g_cleanpump` text,
  `g_tsplit` text,
  `g_pampr` text,
  `g_pampa` text,
  `g_compar` text,
  `g_compaa` text,
  `g_bampr` text,
  `g_bampa` text,
  `g_pdrop` text,
  `sductsize` text NOT NULL,
  `rductsize` text NOT NULL,
  UNIQUE KEY `crecnum` (`crecnum`),
  KEY `crecnum_2` (`crecnum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
CREATE TABLE `status` (
  `answer` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `superheat`
--

DROP TABLE IF EXISTS `superheat`;
CREATE TABLE `superheat` (
  `returnwb` text NOT NULL,
  `outsideair` text NOT NULL,
  `superheat` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `svc_charges`
--

DROP TABLE IF EXISTS `svc_charges`;
CREATE TABLE `svc_charges` (
  `recnum` int(11) NOT NULL auto_increment,
  `callslip` text NOT NULL,
  `quant` decimal(10,2) NOT NULL default '1.00',
  `descript` text NOT NULL,
  `price` decimal(10,2) NOT NULL default '0.00',
  `techid` text,
  `servsync` int(11) default NULL,
  `frcode` int(11) default NULL,
  PRIMARY KEY  (`recnum`),
  UNIQUE KEY `recnum` (`recnum`),
  KEY `recnum_2` (`recnum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `tbllogins`
--

DROP TABLE IF EXISTS `tbllogins`;
CREATE TABLE `tbllogins` (
  `idcode` text NOT NULL,
  `fullname` text NOT NULL,
  `passwd` text NOT NULL,
  `login` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `tblreminders`
--

DROP TABLE IF EXISTS `tblreminders`;
CREATE TABLE `tblreminders` (
  `reminderguid` int(11) NOT NULL auto_increment,
  `duedate` date NOT NULL default '2000-01-01',
  `reminddate` date NOT NULL default '2000-01-01',
  `shortdesc` text NOT NULL,
  `notes` text NOT NULL,
  PRIMARY KEY  (`reminderguid`),
  UNIQUE KEY `reminderguid` (`reminderguid`),
  KEY `reminderguid_2` (`reminderguid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `tech_table`
--

DROP TABLE IF EXISTS `tech_table`;
CREATE TABLE `tech_table` (
  `username` text,
  `tech_name` text NOT NULL,
  `techinit` tinytext NOT NULL,
  `truck_num` text,
  `nate_id` text NOT NULL,
  `allow_delete` text NOT NULL,
  `mod_airbal` text NOT NULL,
  `mod_install` text NOT NULL,
  `mod_servadmin` text NOT NULL,
  `serv_update` text NOT NULL,
  `create_worksheet` text,
  `edit_compphonelist` text,
  `servsync` text,
  `transmit` int(11) default NULL,
  `department` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `tempsplit`
--

DROP TABLE IF EXISTS `tempsplit`;
CREATE TABLE `tempsplit` (
  `returndb` text NOT NULL,
  `returnwb` text NOT NULL,
  `ts` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `idcode` text NOT NULL,
  `fullname` text NOT NULL,
  `passwd` text NOT NULL,
  `login` text NOT NULL,
  `test` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `time_cats`
--

DROP TABLE IF EXISTS `time_cats`;
CREATE TABLE `time_cats` (
  `catnum` int(11) NOT NULL auto_increment,
  `category` text NOT NULL,
  `code` text,
  PRIMARY KEY  (`catnum`),
  UNIQUE KEY `catnum` (`catnum`),
  KEY `catnum_2` (`catnum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `time_sheet`
--

DROP TABLE IF EXISTS `time_sheet`;
CREATE TABLE `time_sheet` (
  `login` text,
  `tdate` date NOT NULL default '2000-01-01',
  `callslip` text NOT NULL,
  `customer` text NOT NULL,
  `time_in` time NOT NULL default '00:00:00',
  `time_out` time NOT NULL default '00:00:00',
  `item_sold` text,
  `amount` decimal(10,2) default NULL,
  `citem_sold` text,
  `camount` decimal(10,2) default NULL,
  `amount_collected` decimal(10,2) default NULL,
  `commision` decimal(10,2) default NULL,
  `tsid` int(11) NOT NULL auto_increment,
  `dispatch_time` time NOT NULL default '00:00:00',
  `ctype` text,
  `servsync` int(11) default NULL,
  UNIQUE KEY `tsid` (`tsid`),
  KEY `tsid_2` (`tsid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `login` varchar(15) NOT NULL default '',
  `passwd` varchar(15) NOT NULL default '',
  `role` varchar(15) NOT NULL default '',
  `idcode` varchar(15) NOT NULL default '',
  PRIMARY KEY  (`login`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `version`
--

DROP TABLE IF EXISTS `version`;
CREATE TABLE `version` (
  `vnumber` text NOT NULL,
  `vdate` date NOT NULL default '0000-00-00'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `wintype`
--

DROP TABLE IF EXISTS `wintype`;
CREATE TABLE `wintype` (
  `wintype` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `worksheet`
--

DROP TABLE IF EXISTS `worksheet`;
CREATE TABLE `worksheet` (
  `wsrec` int(11) NOT NULL auto_increment,
  `crec` int(11) NOT NULL default '0',
  `wsdesc` text NOT NULL,
  `wsdate` date NOT NULL default '2000-01-01',
  `wsnotes` text NOT NULL,
  `wsmult` decimal(10,2) NOT NULL default '0.00',
  `custsite` text,
  `sitenum` text,
  `servsync` int(11) default NULL,
  `techid` text,
  PRIMARY KEY  (`wsrec`),
  KEY `wsrec` (`wsrec`,`crec`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `wsconfig`
--

DROP TABLE IF EXISTS `wsconfig`;
CREATE TABLE `wsconfig` (
  `laborcost` decimal(10,2) default NULL,
  `partmult` decimal(10,2) default NULL,
  `markupdiviser` decimal(10,2) default NULL,
  `gptolabor` decimal(10,2) default NULL,
  `propprice` text NOT NULL,
  `crewbillable` decimal(10,2) NOT NULL default '0.00',
  `labmult` double NOT NULL default '0',
  KEY `laborcost` (`laborcost`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `wsitem`
--

DROP TABLE IF EXISTS `wsitem`;
CREATE TABLE `wsitem` (
  `wsrec` int(11) NOT NULL default '0',
  `itemrec` int(11) NOT NULL auto_increment,
  `item` text NOT NULL,
  `keycode` text NOT NULL,
  `quantity` int(11) NOT NULL default '0',
  `cost` decimal(10,2) NOT NULL default '0.00',
  `laborhours` int(11) NOT NULL default '0',
  `laborcost` decimal(10,2) NOT NULL default '0.00',
  `shophours` int(11) NOT NULL default '0',
  `custsite` text,
  `sitenum` text,
  `servsync` int(11) default NULL,
  `techid` text,
  UNIQUE KEY `itemrec_2` (`itemrec`),
  KEY `itemrec` (`itemrec`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `yesno`
--

DROP TABLE IF EXISTS `yesno`;
CREATE TABLE `yesno` (
  `answer` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

