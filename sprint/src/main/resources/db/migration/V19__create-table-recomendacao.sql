CREATE TABLE Recomendacao (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT,
    tratamento_id BIGINT,
    data_recomendacao DATE NOT NULL,
    motivo VARCHAR(255),
    ativo TINYINT DEFAULT 1,
    FOREIGN KEY (paciente_id) REFERENCES pacientes(id),
    FOREIGN KEY (tratamento_id) REFERENCES tratamentos(id)
);