package part1;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SqlScripts {
    public String INSERT_PATIENT = "INSERT INTO patients (surname, name, patronymic, birth_date, diagnosis) VALUES (?, ?, ?, ?, ?)";
    public String INSERT_DOCTOR = "INSERT INTO doctors (surname, name, patronymic, birth_date, position, specialization) VALUES (?, ?, ?, ?, ?, ?)";
}
