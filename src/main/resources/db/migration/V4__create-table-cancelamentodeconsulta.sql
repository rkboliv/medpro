CREATE TABLE cancelamentodeconsulta (
    id BIGINT NOT NULL AUTO_INCREMENT,
    id_paciente BIGINT NOT NULL,
    id_medico BIGINT NOT NULL,
    data_consulta DATETIME NOT NULL,
    motivo_consulta VARCHAR(255),
    status ENUM('AGENDADA', 'REALIZADA', 'CANCELADA') DEFAULT 'AGENDADA',
    PRIMARY KEY(id),
    FOREIGN KEY (id_paciente) REFERENCES pacientes(id),
    FOREIGN KEY (id_medico) REFERENCES medicos(id)
);
