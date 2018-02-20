-- describe facts;
-- describe authors;
-- describe collaborations;
-- describe dates;
-- describe publications;
-- describe  squads;
-- describe supports;
  describe publications1;

-- SELECT count(*) as facts FROM facts;
-- SELECT count(*) as authors FROM authors;
-- SELECT count(*) as collaborations FROM collaborations;
-- SELECT count(*) as dates FROM dates;
-- SELECT count(*) as publications FROM publications;
-- SELECT count(*) as squads FROM squads;
-- SELECT count(*) as supports FROM supports;

-- FACTS=  473176 lignes
-- AUTHORS = 454807 lignes
-- COLLABORATIONS = 1212896 lignes
-- DATES = 407 lignes
-- PUBLICATIONS = 481659 lignes
-- SQUADS = 479540 lignes
-- SUPPORTS = 11243 lignes




 -- explain plan for SELECT count(*) as publications FROM publications;
 -- select * from table(dbms_xplan.display);


-- /R1

 -- explain plan for select title from publications where nb_pages > 20 ;
 -- select * from table(dbms_xplan.display);

-- Explained.
--
-- PLAN_TABLE_OUTPUT
-- --------------------------------------------------------------------------------
-- Plan hash value: 2449362138
--
-- --------------------------------------------------------------------------------
-- --
--
-- | Id  | Operation	  | Name	 | Rows  | Bytes | Cost (%CPU)| Time
--  |
--
-- --------------------------------------------------------------------------------
-- --
--
--
-- PLAN_TABLE_OUTPUT
-- --------------------------------------------------------------------------------
-- |   0 | SELECT STATEMENT  |		 |     1 |    86 |  1916   (1)| 00:00:23
--  |
--
-- |*  1 |  TABLE ACCESS FULL| PUBLICATIONS |     1 |    86 |  1916   (1)| 00:00:23
--  |
--
-- --------------------------------------------------------------------------------
-- --
--
--
-- Predicate Information (identified by operation id):
--
-- PLAN_TABLE_OUTPUT
-- --------------------------------------------------------------------------------
-- ---------------------------------------------------
--
--    1 - filter("NB_PAGES">20)
--
-- 13 rows selected.


-- /R2

-- explain plan for select title from publications natural join facts where date_id = '2008' ;
-- select * from table(dbms_xplan.display);


-- Explained.
--
--
-- PLAN_TABLE_OUTPUT
-- --------------------------------------------------------------------------------
-- Plan hash value: 3725789566
--
-- --------------------------------------------------------------------------------
-- ---
--
-- | Id  | Operation	   | Name	  | Rows  | Bytes | Cost (%CPU)| Time
--   |
--
-- --------------------------------------------------------------------------------
-- ---
--
--
-- PLAN_TABLE_OUTPUT
-- --------------------------------------------------------------------------------
-- |   0 | SELECT STATEMENT   |		  | 37258 |  4875K|  3593   (1)| 00:00:4
-- 4 |
--
-- |*  1 |  HASH JOIN	   |		  | 37258 |  4875K|  3593   (1)| 00:00:4
-- 4 |
--
-- |*  2 |   TABLE ACCESS FULL| FACTS	  | 37258 |  1237K|  1677   (1)| 00:00:2
-- 1 |
--
-- |   3 |   TABLE ACCESS FULL| PUBLICATIONS |   481K|    45M|  1914   (1)| 00:00:2
-- 3 |
--
-- PLAN_TABLE_OUTPUT
-- --------------------------------------------------------------------------------
--
-- --------------------------------------------------------------------------------
-- ---
--
--
-- Predicate Information (identified by operation id):
-- ---------------------------------------------------
--
--    1 - access("PUBLICATIONS"."PUBLICATION_ID"="FACTS"."PUBLICATION_ID")
--    2 - filter("FACTS"."DATE_ID"='2008')
--
-- 16 rows selected.



-- /R3

-- explain plan for select title from publications where publication_id in
-- (select publication_id from facts where date_id = '2008');
-- select * from table(dbms_xplan.display);



-- Explained.
--
--
-- PLAN_TABLE_OUTPUT
-- --------------------------------------------------------------------------------
-- Plan hash value: 2946731326
--
-- --------------------------------------------------------------------------------
-- -----
--
-- | Id  | Operation	     | Name	    | Rows  | Bytes | Cost (%CPU)| Time
--     |
--
-- --------------------------------------------------------------------------------
-- -----
--
--
-- PLAN_TABLE_OUTPUT
-- --------------------------------------------------------------------------------
-- |   0 | SELECT STATEMENT     |		    | 37252 |  4874K|  3593   (1)| 00:00
-- :44 |
--
-- |*  1 |  HASH JOIN RIGHT SEMI|		    | 37252 |  4874K|  3593   (1)| 00:00
-- :44 |
--
-- |*  2 |   TABLE ACCESS FULL  | FACTS	    | 37258 |  1237K|  1677   (1)| 00:00
-- :21 |
--
-- |   3 |   TABLE ACCESS FULL  | PUBLICATIONS |	481K|	 45M|  1914   (1)| 00:00
-- :23 |
--
-- PLAN_TABLE_OUTPUT
-- --------------------------------------------------------------------------------
--
-- --------------------------------------------------------------------------------
-- -----
--
--
-- Predicate Information (identified by operation id):
-- ---------------------------------------------------
--
--    1 - access("PUBLICATION_ID"="PUBLICATION_ID")
--    2 - filter("DATE_ID"='2008')
--
-- 16 rows selected.


-- /R4

-- explain plan for select title from publications natural join facts
-- natural join collaborations natural join authors where
-- last_name= 'Rosenthal' ;
-- select * from table(dbms_xplan.display);

-- Explained.
--
--
-- PLAN_TABLE_OUTPUT
-- --------------------------------------------------------------------------------
-- Plan hash value: 170757635
--
-- --------------------------------------------------------------------------------
-- ---------------
--
-- | Id  | Operation		     | Name	      | Rows  | Bytes | Cost (%C
-- PU)| Time     |
--
-- --------------------------------------------------------------------------------
-- ---------------
--
--
-- PLAN_TABLE_OUTPUT
-- --------------------------------------------------------------------------------
-- |   0 | SELECT STATEMENT	     |		      |     5 |  1120 |  4537
-- (1)| 00:00:55 |
--
-- |   1 |  NESTED LOOPS		     |		      |       |       |
--    |	      |
--
-- |   2 |   NESTED LOOPS		     |		      |     5 |  1120 |  4537
-- (1)| 00:00:55 |
--
-- |*  3 |    HASH JOIN		     |		      |     5 |   620 |  4527
-- (1)| 00:00:55 |
--
-- PLAN_TABLE_OUTPUT
-- --------------------------------------------------------------------------------
--
-- |*  4 |     HASH JOIN		     |		      |     5 |   340 |  2850
-- (1)| 00:00:35 |
--
-- |*  5 |      TABLE ACCESS FULL	     | AUTHORS	      |     2 |    50 |   618
-- (1)| 00:00:08 |
--
-- |   6 |      TABLE ACCESS FULL	     | COLLABORATIONS |  1212K|    49M|  2227
-- (1)| 00:00:27 |
--
-- |   7 |     TABLE ACCESS FULL	     | FACTS	      |   473K|    25M|  1676
--
-- PLAN_TABLE_OUTPUT
-- --------------------------------------------------------------------------------
-- (1)| 00:00:21 |
--
-- |*  8 |    INDEX UNIQUE SCAN	     | PK_PUBLICATION |     1 |       |     1
-- (0)| 00:00:01 |
--
-- |   9 |   TABLE ACCESS BY INDEX ROWID| PUBLICATIONS   |     1 |   100 |     2
-- (0)| 00:00:01 |
--
-- --------------------------------------------------------------------------------
-- ---------------
--
--
-- PLAN_TABLE_OUTPUT
-- --------------------------------------------------------------------------------
--
-- Predicate Information (identified by operation id):
-- ---------------------------------------------------
--
--    3 - access("FACTS"."SQUAD_ID"="COLLABORATIONS"."SQUAD_ID")
--    4 - access("COLLABORATIONS"."AUTHOR_ID"="AUTHORS"."AUTHOR_ID")
--    5 - filter("AUTHORS"."LAST_NAME"='Rosenthal')
--    8 - access("PUBLICATIONS"."PUBLICATION_ID"="FACTS"."PUBLICATION_ID")
--
-- 24 rows selected.


explain plan for select title from publications where nb_pages > 20;
select * from table(dbms_xplan.display);






 -- Name					   Null?    Type
 -- ----------------------------------------- -------- ----------------------------
 -- PUBLICATION_ID 			   NOT NULL VARCHAR2(100)
 -- SQUAD_ID				   NOT NULL VARCHAR2(100)
 -- DATE_ID				   NOT NULL VARCHAR2(20)
 -- SUPPORT_ID				   NOT NULL VARCHAR2(200)
 --
 -- Name					   Null?    Type
 -- ----------------------------------------- -------- ----------------------------
 -- AUTHOR_ID				   NOT NULL VARCHAR2(100)
 -- FIRST_NAME					    VARCHAR2(25)
 -- LAST_NAME					    VARCHAR2(25)
 --
 -- Name					   Null?    Type
 -- ----------------------------------------- -------- ----------------------------
 -- SQUAD_ID				   NOT NULL VARCHAR2(100)
 -- AUTHOR_ID				   NOT NULL VARCHAR2(100)
 --
 -- Name					   Null?    Type
 -- ----------------------------------------- -------- ----------------------------
 -- DATE_ID				   NOT NULL VARCHAR2(20)
 -- YEAR					   NOT NULL NUMBER
 -- MONTH_LABEL					    VARCHAR2(10)
 -- MONTH						    NUMBER
 -- DAY						    NUMBER
 --
 -- Name					   Null?    Type
 -- ----------------------------------------- -------- ----------------------------
 -- PUBLICATION_ID 			   NOT NULL VARCHAR2(100)
 -- TITLE						    VARCHAR2(500)
 -- ABSTRACT					    VARCHAR2(1500)
 -- NB_PAGES					    NUMBER
 --
 -- Name					   Null?    Type
 -- ----------------------------------------- -------- ----------------------------
 -- SQUAD_ID				   NOT NULL VARCHAR2(100)
 --
 -- Name					   Null?    Type
 -- ----------------------------------------- -------- ----------------------------
 -- SUPPORT_ID				   NOT NULL VARCHAR2(200)
 -- TYPE						    VARCHAR2(15)
 -- NUM						    VARCHAR2(30)
 --
 --
