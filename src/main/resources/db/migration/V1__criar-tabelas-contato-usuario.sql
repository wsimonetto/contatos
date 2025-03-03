CREATE SEQUENCE SEQ_CONTATO
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE SEQUENCE SEQ_USUARIO
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE TABLE TBL_CONTATO (
    ID_CONTATO INTEGER DEFAULT SEQ_CONTATO.NEXTVAL NOT NULL,
    NOME VARCHAR2(100) NOT NULL,
    EMAIL VARCHAR2(100) NOT NULL,
    SENHA VARCHAR2(100) NOT NULL,
    DATA_NASCIMENTO DATE NOT NULL
);

CREATE TABLE TBL_USUARIO (
   ID_USUARIO INTEGER DEFAULT SEQ_USUARIO.NEXTVAL NOT NULL,
   NOME VARCHAR2(100) NOT NULL,
   EMAIL VARCHAR2(100) UNIQUE NOT NULL,
   SENHA VARCHAR2(100) NOT NULL,
   ROLE VARCHAR2(50) DEFAULT 'USER'
);