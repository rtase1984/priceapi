DROP TABLE IF EXISTS PRICES;

CREATE TABLE PRICES (
    id long not null AUTO_INCREMENT,
    BRAND_ID long not null,
    START_DATE timestamp not null,
    END_DATE timestamp not null,
    PRICE_LIST long not null,
    PRODUCT_ID long not null,
    PRIORITY int not null,
    PRICE double not null,
    CURR varchar(5) not null,
    primary key (id)
);