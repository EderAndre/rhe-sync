CREATE TABLE rhe_dados_funcional(

    NUMFUNC varchar(50) NOT NULL,
    NUMVINC varchar(50) NOT NULL,
    CPF varchar(50) NULL,
    NOME varchar(50) NULL,
    NUMRG varchar(50) NULL,
    UFRG varchar(50) NULL,
    SEXO varchar(50) NULL,
    DTNASC date NULL,
    PAI varchar(50) NULL,
    MAE varchar(50) NULL,
    ESTCIVIL varchar(50) NULL,
    ESCOLARIDADE varchar(50) NULL,
    SETOR varchar(50) NULL,
    SETOR_LOTACAO varchar(50) NULL,
    NOMELOGENDER varchar(50) NULL,
    TIPOLOGENDER varchar(50) NULL,
    NUMENDER varchar(50) NULL,
    BAIRROENDER varchar(50) NULL,
    CEPENDER varchar(50) NULL,
    COMPLENDER varchar(50) NULL,
    UFENDER varchar(50) NULL,
    CIDADEENDER varchar(50) NULL,
    TELEFONE varchar(50) NULL,
    NUMTEL_CELULAR varchar(50) NULL,
    NACIONALIDADE varchar(50) NULL,
    DTVAC varchar(50) NULL,
    DTAPOSENT varchar(50) NULL,
    TIPOVINC varchar(50) NULL,
    REGIMEJUR varchar(50) NULL,
    DTEXERC varchar(50) NULL,
    DTPOSSE varchar(50) NULL,
    DTNOM varchar(50) NULL,
    MATRICULA varchar(50) NULL,
    E_MAIL varchar(50) NULL,
    BANCO varchar(50) NULL,
    AGENCIA varchar(50) NULL,
    CONTA varchar(50) NULL,
    PISPASEP varchar(50) NULL,
    COD_CIDNASC varchar(50) NULL,
    COD_CIDENDER varchar(50) NULL,
    DTINI_CESSAO varchar(50) NULL,
    DTAFAST varchar(50) NULL,
    SIT_RHE varchar(50) NULL,
    G_SANGUINEO varchar(50) NULL,
    RACA varchar(50) NULL,
    DEFICIENTE varchar(50) NULL,
    TIPODEFIC varchar(50) NULL,
    OBSERV varchar(500) NULL,
    ORGAORG varchar(50) NULL,
    EXPEDRG varchar(50) NULL,
    NUMTITEL varchar(50) NULL,
    ZONATITEL varchar(50) NULL,
    SECTITEL varchar(50) NULL,
    UFTITEL varchar(50) NULL,
    NUMDOCMILI varchar(50) NULL,
    DOCMILITAR varchar(50) NULL,
    SERDOCMILI varchar(50) NULL,
    CATMILI varchar(50) NULL,
    UFDOCMILI varchar(50) NULL,
    FORCA varchar(50) NULL,
    CNH varchar(50) NULL,
    CATCNH varchar(50) NULL,
    ORGEXPCNH varchar(50) NULL,
    DATAEXPCNH varchar(50) NULL,
    UFCNH varchar(50) NULL,
    VALIDCNH varchar(50) NULL,
    IDENTPROF varchar(50) NULL,
    TIPOIDPROF varchar(50) NULL,
    UF_IDENTPROF varchar(50) NULL,
    DTEXPIDENTPROF varchar(50) NULL,
    PRIMARY KEY  (NUMFUNC,NUMVINC)
);
CREATE TABLE rhe_dados_afast(
    CHAVE bigint(15) NOT NULL,
    NUMFUNC int(10) NULL,
    NUMVINC int(10) NULL,
    DTINI date NULL,
    DTFIM date NULL,
    MOTIVO varchar(60) NULL,
    DTPREVFIM date NULL,
    primary key(CHAVE)
);
CREATE TABLE rhe_mestre_pais(

    ID_PAIS int (11) NOT NULL,
    NACIONALIDADE varchar(255) NULL,
    primary key (ID_PAIS)
);
CREATE TABLE rhe_mestre_cargos(

    COD_CARGO_FUNCAO varchar(255) NOT NULL,
    NOME_CARGO_FUNCAO  varchar(255) NULL,
    TIPO_CARGO  varchar(255) NULL,
    CARGO_FUNCAO  varchar(5) NULL,
    CATEGORIA  varchar(20) NULL,
    SUBCATEGORIA  varchar(20) NULL,
    DT_EXTINCAO date NULL,
    primary key (COD_CARGO_FUNCAO)
);
CREATE TABLE rhe_mestre_subcateg(

    ROWID int(11) AUTO_INCREMENT NOT NULL,
    CATEGORIA varchar(20) NULL,
    SIGLA varchar(20) NULL,
    primary key (ROWID)
);
CREATE TABLE rhe_mestre_tipoevento(

    ROWID int(11) AUTO_INCREMENT NOT NULL,
    TIPOEVENTO varchar(20) NULL,
    NOME varchar(60) NULL,
    NATUREZA varchar(40) NULL,
    NATUREZA_PRINCIPAL varchar(20) NULL,
    TIPO_CARGO varchar(40) NULL,
    primary key (ROWID)
);
CREATE TABLE rhe_mestre_trinomio(

    ROWID int(11) AUTO_INCREMENT NOT NULL,
    TIPOVINC varchar(20) NULL,
    CATEGORIA varchar(20) NULL,
    REGIMEJUR varchar(20) NULL,
    primary key (ROWID)
);

CREATE TABLE rhe_mestre_cidades(

    ID_CIDADE int (11) NOT NULL,
    NOME_CIDADE varchar(255) NULL,
    primary key (ID_CIDADE)
);
CREATE TABLE rhe_mestre_setor(

    EMP_CODIGO varchar(50) NOT NULL,
    SETOR varchar(50) NOT NULL,
    PAISETOR varchar(50) NULL,
    NOMESETOR varchar(255) NULL,
    NOMESETORLONGO varchar(255) NULL,
    DATAINI date NULL,
    SETOR_CORP varchar(50) NULL,
    HLOCAL varchar(50) NULL,
    TIPOSETOR varchar(50) NULL,
    TIPOLOG varchar(50) NULL,
    NOMELOG varchar(255) NULL,
    NUMEROLOG varchar(50) NULL,
    COMPLEMENTO varchar(50) NULL,
    BAIRRO varchar(50) NULL,
    CEP varchar(50) NULL,
    CIDADE varchar(50) NULL,
    UF varchar(50) NULL,
    FONE varchar(50) NULL,
    FAX varchar(50) NULL,
    ORGANIZACAO varchar(50) NULL,
    SIGLA varchar(50) NULL,
    PRIMARY KEY  (EMP_CODIGO,SETOR)
);

CREATE TABLE rhe_dados_publicacao(

    ROWID int(11) AUTO_INCREMENT NOT NULL,
    NUMFUNC varchar(50) NULL,
    NUMVINC varchar(50) NULL,
    TIPOEVENTO varchar(50) NULL,
    NUMPUBL varchar(50) NULL,
    DATAPUBL date NULL,
    TIPOPUBL varchar(50) NULL,
    DATADO date NULL,
    TIPODO varchar(50) NULL,
    AUTORIDADE varchar(50) NULL,
    NUMERO_PROCESSO varchar(50) NULL,
    MOTIVO varchar(50) NULL,
    OBS varchar(255)NULL,
    PUBLICACAO varchar(50) NULL,
        primary key (ROWID)
);
CREATE TABLE rhe_dados_vinculo(

    NUMFUNC int(11) NOT NULL,
    NUMVINC int(11) NOT NULL,
    DTVAC date NULL,
    DTAPOSENT date NULL,
    TIPOVINC varchar(50) NULL,
    REGIMEJUR varchar(50) NULL,
    DTEXERC date NULL,
    DTPOSSE date NULL,
    DTNOM date NULL,
    MATRICULA varchar(50) NULL,
    BANCO varchar(50) NULL,
    AGENCIA varchar(50) NULL,
    CONTA varchar(50) NULL,
    MATRICULA1 varchar(50) NULL,
    CLASSIFCONC varchar(50) NULL,
    REGPREV varchar(50) NULL,
    LIMITELC varchar(50) NULL,
    EMAILCORP varchar(50) NULL,
    PRIMARY KEY  (NUMFUNC,NUMVINC)
);
CREATE TABLE rhe_dados_evento(

    NUMEV varchar(50) NOT NULL,
    NUMFUNC varchar(50) NULL,
    NUMVINC varchar(50) NULL,
    CARGO varchar(50) NULL,
    DTINI date NULL,
    DTFIM date NULL,
    TIPOEVENTO varchar(50) NULL,
    REFERENCIA varchar(50) NULL,
    CARGO_FUNCAO varchar(50) NULL,
    SETOR varchar(50) NULL,
    ESPECIEEVENTO varchar(50) NULL,
    FRACAO varchar(50) NULL,
    REPR varchar(50) NULL,
    PROVESP varchar(50) NULL,
    OBS text NULL,
    primary key(NUMEV)
);

CREATE TABLE rhe_layout_lancamento_manual(
    Emp_Codigo varchar(2) NULL,
    NumFunc varchar(8) NULL,
    NumVinc varchar(2) NULL,
    NumPens varchar(2) NULL,
    DtIni varchar(8) NULL,
    DtFim varchar(8) NULL,
    Rubrica varchar(4) NULL,
    Sinal varchar(1) NULL,
    Valor varchar(10) NULL,
    Tipo_Movimento varchar(15) NULL,
    Indicador varchar(1) NULL,
    Projeto_Atividade varchar(6) NULL,
    Compl_Reduzido varchar(15) NULL,
    Final_Calculo varchar(30) NULL,
    Procedencia varchar(50) NULL,
    Info varchar(10) NULL,
    Observacao varchar(255) NULL
);
