CREATE TABLE rhe_dados_capacitacoes(
    NUMFUNC int NOT NULL,
    EVENTO int NOT NULL,
    NOME varchar(255) NULL,
    DATA date NULL,
    CARGAHORARIA varchar(255) NULL,
    ENTIDADE varchar(255) NULL,
    OBS varchar(510) NULL,
    CREATED datetime NULL,
    LASTUPDATED datetime NULL,
    primary key (NUMFUNC,EVENTO)
);

