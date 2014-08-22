##############################
######Creating Log table######
##############################

CREATE TABLE jqgridLogs
   (USER_ID VARCHAR(20) NOT NULL,
    DATED   TIMESTAMP NOT NULL,
    LOGGER  VARCHAR(500) NOT NULL,
    LEVEL   VARCHAR(10) NOT NULL,
    MESSAGE VARCHAR(10000) NOT NULL
);