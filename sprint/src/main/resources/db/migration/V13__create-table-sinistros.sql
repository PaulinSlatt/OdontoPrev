CREATE TABLE sinistros (
                           id_sinistro BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                           paciente_id BIGINT,
                           tratamento_id BIGINT,
                           data_ocorrencia DATE NOT NULL,
                           descricao VARCHAR(255),
                           valor_reembolsado VARCHAR(255),
                           status VARCHAR(50),
                           ativo TINYINT DEFAULT 1,
                           CONSTRAINT chk_status CHECK (status IN ('Aprovado', 'Negado', 'Em Análise')),
                           FOREIGN KEY (paciente_id) REFERENCES pacientes(id),
                           FOREIGN KEY (tratamento_id) REFERENCES tratamentos(id)
);