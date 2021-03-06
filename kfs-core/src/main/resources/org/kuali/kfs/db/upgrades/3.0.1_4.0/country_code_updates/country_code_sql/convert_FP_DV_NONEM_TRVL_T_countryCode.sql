--
-- The Kuali Financial System, a comprehensive financial management system for higher education.
-- 
-- Copyright 2005-2014 The Kuali Foundation
-- 
-- This program is free software: you can redistribute it and/or modify
-- it under the terms of the GNU Affero General Public License as
-- published by the Free Software Foundation, either version 3 of the
-- License, or (at your option) any later version.
-- 
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
-- GNU Affero General Public License for more details.
-- 
-- You should have received a copy of the GNU Affero General Public License
-- along with this program.  If not, see <http://www.gnu.org/licenses/>.
--

-- This script will migrate a column in a table from the former Rice country 
-- codes which were based on FIPS 10-4 (with some minor differences) to the new
-- Rice country codes which are based on ISO 3166-1.  This script may not 
-- properly execute on columns with a primary key or unique constraint as some
-- of the former codes have merged (i.e. - all US Minor Outlying Islands have 
-- been unified under a single code, UM).  This script also does not take any 
-- action on codes that are not part of the list of former Rice country codes.
--
-- Table Name: 	FP_DV_NONEM_TRVL_T
-- Column Name: DV_TRVL_TO_CNTRY_CD
--
-- In order to avoid collisions between the former codes and the new codes, the 
-- codes are first changed to an interim, unique code.  Once that change is 
-- complete, they are changed to the new, correct code.
--
-- Change to temporary code
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='A0' where DV_TRVL_TO_CNTRY_CD='AA';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='A1' where DV_TRVL_TO_CNTRY_CD='AC';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='A3' where DV_TRVL_TO_CNTRY_CD='AG';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='A4' where DV_TRVL_TO_CNTRY_CD='AJ';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='A7' where DV_TRVL_TO_CNTRY_CD='AN';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='A9' where DV_TRVL_TO_CNTRY_CD='AQ';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='B1' where DV_TRVL_TO_CNTRY_CD='AS';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='B2' where DV_TRVL_TO_CNTRY_CD='AT';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='B3' where DV_TRVL_TO_CNTRY_CD='AU';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='B4' where DV_TRVL_TO_CNTRY_CD='AV';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='B5' where DV_TRVL_TO_CNTRY_CD='AY';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='B6' where DV_TRVL_TO_CNTRY_CD='BA';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='B8' where DV_TRVL_TO_CNTRY_CD='BC';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='B9' where DV_TRVL_TO_CNTRY_CD='BD';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='C1' where DV_TRVL_TO_CNTRY_CD='BF';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='C2' where DV_TRVL_TO_CNTRY_CD='BG';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='C3' where DV_TRVL_TO_CNTRY_CD='BH';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='C4' where DV_TRVL_TO_CNTRY_CD='BK';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='C5' where DV_TRVL_TO_CNTRY_CD='BL';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='C6' where DV_TRVL_TO_CNTRY_CD='BM';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='C7' where DV_TRVL_TO_CNTRY_CD='BN';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='C8' where DV_TRVL_TO_CNTRY_CD='BO';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='C9' where DV_TRVL_TO_CNTRY_CD='BP';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='D0' where DV_TRVL_TO_CNTRY_CD='BQ';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='D2' where DV_TRVL_TO_CNTRY_CD='BS';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='D4' where DV_TRVL_TO_CNTRY_CD='BU';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='D6' where DV_TRVL_TO_CNTRY_CD='BX';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='D7' where DV_TRVL_TO_CNTRY_CD='BY';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='D9' where DV_TRVL_TO_CNTRY_CD='CB';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='E0' where DV_TRVL_TO_CNTRY_CD='CD';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='E1' where DV_TRVL_TO_CNTRY_CD='CE';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='E2' where DV_TRVL_TO_CNTRY_CD='CF';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='E3' where DV_TRVL_TO_CNTRY_CD='CG';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='E4' where DV_TRVL_TO_CNTRY_CD='CH';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='E5' where DV_TRVL_TO_CNTRY_CD='CI';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='E6' where DV_TRVL_TO_CNTRY_CD='CJ';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='E7' where DV_TRVL_TO_CNTRY_CD='CK';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='E9' where DV_TRVL_TO_CNTRY_CD='CN';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='F1' where DV_TRVL_TO_CNTRY_CD='CQ';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='F2' where DV_TRVL_TO_CNTRY_CD='CR';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='F3' where DV_TRVL_TO_CNTRY_CD='CS';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='F4' where DV_TRVL_TO_CNTRY_CD='CT';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='F7' where DV_TRVL_TO_CNTRY_CD='CW';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='F9' where DV_TRVL_TO_CNTRY_CD='CZ';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='G0' where DV_TRVL_TO_CNTRY_CD='DA';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='G2' where DV_TRVL_TO_CNTRY_CD='DO';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='G3' where DV_TRVL_TO_CNTRY_CD='DR';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='G6' where DV_TRVL_TO_CNTRY_CD='EI';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='G7' where DV_TRVL_TO_CNTRY_CD='EK';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='G8' where DV_TRVL_TO_CNTRY_CD='EN';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='H0' where DV_TRVL_TO_CNTRY_CD='ES';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='H2' where DV_TRVL_TO_CNTRY_CD='EU';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='H3' where DV_TRVL_TO_CNTRY_CD='EZ';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='H4' where DV_TRVL_TO_CNTRY_CD='FA';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='H5' where DV_TRVL_TO_CNTRY_CD='FG';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='I0' where DV_TRVL_TO_CNTRY_CD='FP';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='I1' where DV_TRVL_TO_CNTRY_CD='FQ';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='I3' where DV_TRVL_TO_CNTRY_CD='FS';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='I4' where DV_TRVL_TO_CNTRY_CD='GA';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='I5' where DV_TRVL_TO_CNTRY_CD='GB';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='I6' where DV_TRVL_TO_CNTRY_CD='GE';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='I7' where DV_TRVL_TO_CNTRY_CD='GG';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='J0' where DV_TRVL_TO_CNTRY_CD='GJ';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='J1' where DV_TRVL_TO_CNTRY_CD='GK';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='J3' where DV_TRVL_TO_CNTRY_CD='GM';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='J4' where DV_TRVL_TO_CNTRY_CD='GO';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='J6' where DV_TRVL_TO_CNTRY_CD='GQ';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='J9' where DV_TRVL_TO_CNTRY_CD='GV';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='K1' where DV_TRVL_TO_CNTRY_CD='GZ';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='K2' where DV_TRVL_TO_CNTRY_CD='HA';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='K5' where DV_TRVL_TO_CNTRY_CD='HO';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='K6' where DV_TRVL_TO_CNTRY_CD='HQ';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='K9' where DV_TRVL_TO_CNTRY_CD='IC';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='L4' where DV_TRVL_TO_CNTRY_CD='IP';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='L6' where DV_TRVL_TO_CNTRY_CD='IS';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='L8' where DV_TRVL_TO_CNTRY_CD='IV';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='L9' where DV_TRVL_TO_CNTRY_CD='IY';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='M0' where DV_TRVL_TO_CNTRY_CD='IZ';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='M1' where DV_TRVL_TO_CNTRY_CD='JA';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='M4' where DV_TRVL_TO_CNTRY_CD='JN';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='M6' where DV_TRVL_TO_CNTRY_CD='JQ';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='M7' where DV_TRVL_TO_CNTRY_CD='JU';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='N0' where DV_TRVL_TO_CNTRY_CD='KN';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='N1' where DV_TRVL_TO_CNTRY_CD='KQ';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='N2' where DV_TRVL_TO_CNTRY_CD='KR';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='N3' where DV_TRVL_TO_CNTRY_CD='KS';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='N4' where DV_TRVL_TO_CNTRY_CD='KT';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='N5' where DV_TRVL_TO_CNTRY_CD='KU';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='N8' where DV_TRVL_TO_CNTRY_CD='LE';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='N9' where DV_TRVL_TO_CNTRY_CD='LG';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='O0' where DV_TRVL_TO_CNTRY_CD='LH';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='O1' where DV_TRVL_TO_CNTRY_CD='LI';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='O2' where DV_TRVL_TO_CNTRY_CD='LO';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='O3' where DV_TRVL_TO_CNTRY_CD='LQ';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='O4' where DV_TRVL_TO_CNTRY_CD='LS';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='O5' where DV_TRVL_TO_CNTRY_CD='LT';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='O8' where DV_TRVL_TO_CNTRY_CD='MA';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='O9' where DV_TRVL_TO_CNTRY_CD='MB';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='P0' where DV_TRVL_TO_CNTRY_CD='MC';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='P2' where DV_TRVL_TO_CNTRY_CD='MF';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='P3' where DV_TRVL_TO_CNTRY_CD='MG';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='P4' where DV_TRVL_TO_CNTRY_CD='MH';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='P5' where DV_TRVL_TO_CNTRY_CD='MI';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='P8' where DV_TRVL_TO_CNTRY_CD='MN';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='P9' where DV_TRVL_TO_CNTRY_CD='MO';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='Q0' where DV_TRVL_TO_CNTRY_CD='MP';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='Q1' where DV_TRVL_TO_CNTRY_CD='MQ';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='Q4' where DV_TRVL_TO_CNTRY_CD='MU';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='Q6' where DV_TRVL_TO_CNTRY_CD='MW';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='R0' where DV_TRVL_TO_CNTRY_CD='NA';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='R2' where DV_TRVL_TO_CNTRY_CD='NE';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='R4' where DV_TRVL_TO_CNTRY_CD='NG';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='R5' where DV_TRVL_TO_CNTRY_CD='NH';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='R6' where DV_TRVL_TO_CNTRY_CD='NI';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='S1' where DV_TRVL_TO_CNTRY_CD='NS';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='S2' where DV_TRVL_TO_CNTRY_CD='NU';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='S4' where DV_TRVL_TO_CNTRY_CD='OC';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='S5' where DV_TRVL_TO_CNTRY_CD='PA';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='S6' where DV_TRVL_TO_CNTRY_CD='PC';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='S8' where DV_TRVL_TO_CNTRY_CD='PF';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='S9' where DV_TRVL_TO_CNTRY_CD='PG';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='T2' where DV_TRVL_TO_CNTRY_CD='PM';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='T3' where DV_TRVL_TO_CNTRY_CD='PO';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='T4' where DV_TRVL_TO_CNTRY_CD='PP';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='T5' where DV_TRVL_TO_CNTRY_CD='PS';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='T6' where DV_TRVL_TO_CNTRY_CD='PU';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='T9' where DV_TRVL_TO_CNTRY_CD='RM';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='U1' where DV_TRVL_TO_CNTRY_CD='RP';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='U2' where DV_TRVL_TO_CNTRY_CD='RQ';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='U3' where DV_TRVL_TO_CNTRY_CD='RS';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='U6' where DV_TRVL_TO_CNTRY_CD='SB';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='U7' where DV_TRVL_TO_CNTRY_CD='SC';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='U8' where DV_TRVL_TO_CNTRY_CD='SE';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='U9' where DV_TRVL_TO_CNTRY_CD='SF';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='V0' where DV_TRVL_TO_CNTRY_CD='SG';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='V5' where DV_TRVL_TO_CNTRY_CD='SN';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='V7' where DV_TRVL_TO_CNTRY_CD='SP';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='V8' where DV_TRVL_TO_CNTRY_CD='SR';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='V9' where DV_TRVL_TO_CNTRY_CD='ST';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='W0' where DV_TRVL_TO_CNTRY_CD='SU';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='W1' where DV_TRVL_TO_CNTRY_CD='SV';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='W2' where DV_TRVL_TO_CNTRY_CD='SW';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='W4' where DV_TRVL_TO_CNTRY_CD='SZ';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='W5' where DV_TRVL_TO_CNTRY_CD='TC';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='W6' where DV_TRVL_TO_CNTRY_CD='TD';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='W7' where DV_TRVL_TO_CNTRY_CD='TE';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='W9' where DV_TRVL_TO_CNTRY_CD='TI';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='X0' where DV_TRVL_TO_CNTRY_CD='TK';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='X1' where DV_TRVL_TO_CNTRY_CD='TL';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='X2' where DV_TRVL_TO_CNTRY_CD='TN';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='X3' where DV_TRVL_TO_CNTRY_CD='TO';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='X4' where DV_TRVL_TO_CNTRY_CD='TP';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='X5' where DV_TRVL_TO_CNTRY_CD='TS';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='X6' where DV_TRVL_TO_CNTRY_CD='TU';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='X9' where DV_TRVL_TO_CNTRY_CD='TX';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='Y2' where DV_TRVL_TO_CNTRY_CD='UK';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='Y3' where DV_TRVL_TO_CNTRY_CD='UP';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='Y4' where DV_TRVL_TO_CNTRY_CD='UR';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='Z0' where DV_TRVL_TO_CNTRY_CD='VI';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='Z1' where DV_TRVL_TO_CNTRY_CD='VM';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='Z2' where DV_TRVL_TO_CNTRY_CD='VQ';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='Z3' where DV_TRVL_TO_CNTRY_CD='VT';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='Z4' where DV_TRVL_TO_CNTRY_CD='WA';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='Z5' where DV_TRVL_TO_CNTRY_CD='WE';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='Z7' where DV_TRVL_TO_CNTRY_CD='WI';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='Z8' where DV_TRVL_TO_CNTRY_CD='WQ';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='00' where DV_TRVL_TO_CNTRY_CD='WZ';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='01' where DV_TRVL_TO_CNTRY_CD='YM';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='02' where DV_TRVL_TO_CNTRY_CD='YO';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='03' where DV_TRVL_TO_CNTRY_CD='ZA';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='04' where DV_TRVL_TO_CNTRY_CD='ZI';
-- Change to final code
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='AW' where DV_TRVL_TO_CNTRY_CD='A0';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='AG' where DV_TRVL_TO_CNTRY_CD='A1';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='DZ' where DV_TRVL_TO_CNTRY_CD='A3';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='AZ' where DV_TRVL_TO_CNTRY_CD='A4';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='AD' where DV_TRVL_TO_CNTRY_CD='A7';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='AS' where DV_TRVL_TO_CNTRY_CD='A9';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='AU' where DV_TRVL_TO_CNTRY_CD='B1';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='AU' where DV_TRVL_TO_CNTRY_CD='B2';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='AT' where DV_TRVL_TO_CNTRY_CD='B3';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='AI' where DV_TRVL_TO_CNTRY_CD='B4';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='AQ' where DV_TRVL_TO_CNTRY_CD='B5';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='BH' where DV_TRVL_TO_CNTRY_CD='B6';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='BW' where DV_TRVL_TO_CNTRY_CD='B8';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='BM' where DV_TRVL_TO_CNTRY_CD='B9';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='BS' where DV_TRVL_TO_CNTRY_CD='C1';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='BD' where DV_TRVL_TO_CNTRY_CD='C2';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='BZ' where DV_TRVL_TO_CNTRY_CD='C3';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='BA' where DV_TRVL_TO_CNTRY_CD='C4';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='BO' where DV_TRVL_TO_CNTRY_CD='C5';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='MM' where DV_TRVL_TO_CNTRY_CD='C6';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='BJ' where DV_TRVL_TO_CNTRY_CD='C7';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='BY' where DV_TRVL_TO_CNTRY_CD='C8';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='SB' where DV_TRVL_TO_CNTRY_CD='C9';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='UM' where DV_TRVL_TO_CNTRY_CD='D0';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='TF' where DV_TRVL_TO_CNTRY_CD='D2';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='BG' where DV_TRVL_TO_CNTRY_CD='D4';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='BN' where DV_TRVL_TO_CNTRY_CD='D6';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='BI' where DV_TRVL_TO_CNTRY_CD='D7';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='KH' where DV_TRVL_TO_CNTRY_CD='D9';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='TD' where DV_TRVL_TO_CNTRY_CD='E0';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='LK' where DV_TRVL_TO_CNTRY_CD='E1';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='CG' where DV_TRVL_TO_CNTRY_CD='E2';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='CD' where DV_TRVL_TO_CNTRY_CD='E3';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='CN' where DV_TRVL_TO_CNTRY_CD='E4';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='CL' where DV_TRVL_TO_CNTRY_CD='E5';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='KY' where DV_TRVL_TO_CNTRY_CD='E6';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='CC' where DV_TRVL_TO_CNTRY_CD='E7';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='KM' where DV_TRVL_TO_CNTRY_CD='E9';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='MP' where DV_TRVL_TO_CNTRY_CD='F1';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='AU' where DV_TRVL_TO_CNTRY_CD='F2';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='CR' where DV_TRVL_TO_CNTRY_CD='F3';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='CF' where DV_TRVL_TO_CNTRY_CD='F4';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='CK' where DV_TRVL_TO_CNTRY_CD='F7';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='CS' where DV_TRVL_TO_CNTRY_CD='F9';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='DK' where DV_TRVL_TO_CNTRY_CD='G0';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='DM' where DV_TRVL_TO_CNTRY_CD='G2';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='DO' where DV_TRVL_TO_CNTRY_CD='G3';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='IE' where DV_TRVL_TO_CNTRY_CD='G6';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='GQ' where DV_TRVL_TO_CNTRY_CD='G7';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='EE' where DV_TRVL_TO_CNTRY_CD='G8';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='SV' where DV_TRVL_TO_CNTRY_CD='H0';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='TF' where DV_TRVL_TO_CNTRY_CD='H2';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='CZ' where DV_TRVL_TO_CNTRY_CD='H3';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='FK' where DV_TRVL_TO_CNTRY_CD='H4';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='GF' where DV_TRVL_TO_CNTRY_CD='H5';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='PF' where DV_TRVL_TO_CNTRY_CD='I0';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='UM' where DV_TRVL_TO_CNTRY_CD='I1';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='TF' where DV_TRVL_TO_CNTRY_CD='I3';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='GM' where DV_TRVL_TO_CNTRY_CD='I4';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='GA' where DV_TRVL_TO_CNTRY_CD='I5';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='DE' where DV_TRVL_TO_CNTRY_CD='I6';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='GE' where DV_TRVL_TO_CNTRY_CD='I7';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='GD' where DV_TRVL_TO_CNTRY_CD='J0';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='GG' where DV_TRVL_TO_CNTRY_CD='J1';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='DE' where DV_TRVL_TO_CNTRY_CD='J3';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='TF' where DV_TRVL_TO_CNTRY_CD='J4';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='GU' where DV_TRVL_TO_CNTRY_CD='J6';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='GN' where DV_TRVL_TO_CNTRY_CD='J9';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='PS' where DV_TRVL_TO_CNTRY_CD='K1';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='HT' where DV_TRVL_TO_CNTRY_CD='K2';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='HN' where DV_TRVL_TO_CNTRY_CD='K5';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='UM' where DV_TRVL_TO_CNTRY_CD='K6';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='IS' where DV_TRVL_TO_CNTRY_CD='K9';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='FR' where DV_TRVL_TO_CNTRY_CD='L4';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='IL' where DV_TRVL_TO_CNTRY_CD='L6';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='CI' where DV_TRVL_TO_CNTRY_CD='L8';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='NT' where DV_TRVL_TO_CNTRY_CD='L9';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='IQ' where DV_TRVL_TO_CNTRY_CD='M0';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='JP' where DV_TRVL_TO_CNTRY_CD='M1';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='NO' where DV_TRVL_TO_CNTRY_CD='M4';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='UM' where DV_TRVL_TO_CNTRY_CD='M6';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='TF' where DV_TRVL_TO_CNTRY_CD='M7';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='KP' where DV_TRVL_TO_CNTRY_CD='N0';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='UM' where DV_TRVL_TO_CNTRY_CD='N1';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='KI' where DV_TRVL_TO_CNTRY_CD='N2';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='KR' where DV_TRVL_TO_CNTRY_CD='N3';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='CX' where DV_TRVL_TO_CNTRY_CD='N4';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='KW' where DV_TRVL_TO_CNTRY_CD='N5';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='LB' where DV_TRVL_TO_CNTRY_CD='N8';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='LV' where DV_TRVL_TO_CNTRY_CD='N9';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='LT' where DV_TRVL_TO_CNTRY_CD='O0';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='LR' where DV_TRVL_TO_CNTRY_CD='O1';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='SK' where DV_TRVL_TO_CNTRY_CD='O2';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='UM' where DV_TRVL_TO_CNTRY_CD='O3';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='LI' where DV_TRVL_TO_CNTRY_CD='O4';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='LS' where DV_TRVL_TO_CNTRY_CD='O5';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='MG' where DV_TRVL_TO_CNTRY_CD='O8';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='MQ' where DV_TRVL_TO_CNTRY_CD='O9';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='MO' where DV_TRVL_TO_CNTRY_CD='P0';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='YT' where DV_TRVL_TO_CNTRY_CD='P2';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='MN' where DV_TRVL_TO_CNTRY_CD='P3';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='MS' where DV_TRVL_TO_CNTRY_CD='P4';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='MW' where DV_TRVL_TO_CNTRY_CD='P5';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='MC' where DV_TRVL_TO_CNTRY_CD='P8';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='MA' where DV_TRVL_TO_CNTRY_CD='P9';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='MU' where DV_TRVL_TO_CNTRY_CD='Q0';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='UM' where DV_TRVL_TO_CNTRY_CD='Q1';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='OM' where DV_TRVL_TO_CNTRY_CD='Q4';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='ME' where DV_TRVL_TO_CNTRY_CD='Q6';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='AN' where DV_TRVL_TO_CNTRY_CD='R0';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='NU' where DV_TRVL_TO_CNTRY_CD='R2';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='NE' where DV_TRVL_TO_CNTRY_CD='R4';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='VU' where DV_TRVL_TO_CNTRY_CD='R5';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='NG' where DV_TRVL_TO_CNTRY_CD='R6';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='SR' where DV_TRVL_TO_CNTRY_CD='S1';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='NI' where DV_TRVL_TO_CNTRY_CD='S2';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='ZZ' where DV_TRVL_TO_CNTRY_CD='S4';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='PY' where DV_TRVL_TO_CNTRY_CD='S5';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='PN' where DV_TRVL_TO_CNTRY_CD='S6';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='XP' where DV_TRVL_TO_CNTRY_CD='S8';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='XS' where DV_TRVL_TO_CNTRY_CD='S9';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='PA' where DV_TRVL_TO_CNTRY_CD='T2';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='PT' where DV_TRVL_TO_CNTRY_CD='T3';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='PG' where DV_TRVL_TO_CNTRY_CD='T4';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='PW' where DV_TRVL_TO_CNTRY_CD='T5';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='GW' where DV_TRVL_TO_CNTRY_CD='T6';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='MH' where DV_TRVL_TO_CNTRY_CD='T9';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='PH' where DV_TRVL_TO_CNTRY_CD='U1';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='PR' where DV_TRVL_TO_CNTRY_CD='U2';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='RU' where DV_TRVL_TO_CNTRY_CD='U3';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='PM' where DV_TRVL_TO_CNTRY_CD='U6';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='KN' where DV_TRVL_TO_CNTRY_CD='U7';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='SC' where DV_TRVL_TO_CNTRY_CD='U8';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='ZA' where DV_TRVL_TO_CNTRY_CD='U9';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='SN' where DV_TRVL_TO_CNTRY_CD='V0';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='SG' where DV_TRVL_TO_CNTRY_CD='V5';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='ES' where DV_TRVL_TO_CNTRY_CD='V7';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='RS' where DV_TRVL_TO_CNTRY_CD='V8';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='LC' where DV_TRVL_TO_CNTRY_CD='V9';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='SD' where DV_TRVL_TO_CNTRY_CD='W0';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='SJ' where DV_TRVL_TO_CNTRY_CD='W1';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='SE' where DV_TRVL_TO_CNTRY_CD='W2';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='CH' where DV_TRVL_TO_CNTRY_CD='W4';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='AE' where DV_TRVL_TO_CNTRY_CD='W5';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='TT' where DV_TRVL_TO_CNTRY_CD='W6';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='TF' where DV_TRVL_TO_CNTRY_CD='W7';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='TJ' where DV_TRVL_TO_CNTRY_CD='W9';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='TC' where DV_TRVL_TO_CNTRY_CD='X0';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='TK' where DV_TRVL_TO_CNTRY_CD='X1';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='TO' where DV_TRVL_TO_CNTRY_CD='X2';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='TG' where DV_TRVL_TO_CNTRY_CD='X3';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='ST' where DV_TRVL_TO_CNTRY_CD='X4';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='TN' where DV_TRVL_TO_CNTRY_CD='X5';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='TR' where DV_TRVL_TO_CNTRY_CD='X6';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='TM' where DV_TRVL_TO_CNTRY_CD='X9';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='GB' where DV_TRVL_TO_CNTRY_CD='Y2';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='UA' where DV_TRVL_TO_CNTRY_CD='Y3';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='SU' where DV_TRVL_TO_CNTRY_CD='Y4';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='VG' where DV_TRVL_TO_CNTRY_CD='Z0';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='VN' where DV_TRVL_TO_CNTRY_CD='Z1';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='VI' where DV_TRVL_TO_CNTRY_CD='Z2';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='VA' where DV_TRVL_TO_CNTRY_CD='Z3';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='NA' where DV_TRVL_TO_CNTRY_CD='Z4';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='PS' where DV_TRVL_TO_CNTRY_CD='Z5';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='EH' where DV_TRVL_TO_CNTRY_CD='Z7';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='UM' where DV_TRVL_TO_CNTRY_CD='Z8';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='SZ' where DV_TRVL_TO_CNTRY_CD='00';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='YE' where DV_TRVL_TO_CNTRY_CD='01';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='YU' where DV_TRVL_TO_CNTRY_CD='02';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='ZM' where DV_TRVL_TO_CNTRY_CD='03';
UPDATE FP_DV_NONEM_TRVL_T SET DV_TRVL_TO_CNTRY_CD='ZW' where DV_TRVL_TO_CNTRY_CD='04';

