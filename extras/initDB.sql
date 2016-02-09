CREATE DATABASE card_db;
CREATE USER CARD_USER WITH password '123';
GRANT ALL privileges ON DATABASE card_db TO CARD_USER;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO CARD_USER;
		DROP TABLE V_DISCOUNT;
CREATE OR REPLACE VIEW V_DISCOUNT
AS
SELECT
Q.CARD_ID,
CASE WHEN Q.TOTAL_AMOUNT > 5000 THEN 5
	WHEN Q.TOTAL_AMOUNT > 10000 THEN 10
	ELSE 0
END AS DISCOUNT,
Q.TOTAL_AMOUNT
FROM
(
SELECT
CARD_ID,
SUM(AMOUNT) AS TOTAL_AMOUNT
FROM RECEIPT
group BY CARD_ID)Q;
