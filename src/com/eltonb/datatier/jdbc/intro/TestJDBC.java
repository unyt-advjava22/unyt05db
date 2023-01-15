package com.eltonb.datatier.jdbc.intro;

import org.apache.derby.jdbc.ClientDriver;

import java.sql.*;

public class TestJDBC {

    public static void main(String[] args) {
        TestJDBC t = new TestJDBC();
        try {
            //t.retrieveStudents("CS'; drop table STUDENTS");
            t.createNewDepartment();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void retrieveStudents(String deptCode) throws Exception {
        String query = "select * from students where department_code = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/uni", "APP", "APP")) {
            try (PreparedStatement stat = conn.prepareStatement(query)) {
                stat.setString(1, deptCode);
                ResultSet rs = stat.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String name = rs.getString("NAME");
                    String surname = rs.getString("SURNAME");
                    String dept = rs.getString("DEPARTMENT_CODE");
                    String email = rs.getString("EMAIL");
                    System.out.printf("%d, %s %s - %s - %s\n", id, name, surname, dept, email);
                }
            }
        }
    }

    private void createNewDepartment() throws SQLException {
        /*
            nd = create new department
            ni = create new instructor in department nd
            update department nd set chair_id = ni
         */
        final String DEPT_CODE = "PSY";
        try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/uni", "APP", "APP")) {
            String newDeptSql = "insert into departments(CODE, NAME, FACULTY_CODE) values(?, ?, ?)";

            try (var stat = conn.prepareStatement(newDeptSql)) {
                stat.setString(1, DEPT_CODE);
                stat.setString(2, "Psychology");
                stat.setString(3, "SS");
                int rowCount = stat.executeUpdate();
                System.out.println(rowCount + " departments records affected");
            }

            String newInsSql = "insert into instructors values(?, ?, ?, ?)";
            int instructorId = nextInstructorId(conn);

            try (var stat = conn.prepareStatement(newInsSql)) {
                stat.setInt(1, instructorId);
                stat.setString(2, "Sigmund");
                stat.setString(3, "Freud");
                stat.setString(4, DEPT_CODE);
                int rowCount = stat.executeUpdate();
                System.out.println(rowCount + " instructors records affected");
            }

            String updDepartmentSql = "update departments set chair_id = ? where code = ?";
            try (var stat = conn.prepareStatement(updDepartmentSql)) {
                stat.setInt(1, instructorId);
                stat.setString(2, DEPT_CODE);
                int rowCount = stat.executeUpdate();
                System.out.println(rowCount + " rows of departments affected");
            }
        }

    }

    private int nextInstructorId(Connection conn) throws SQLException {
        var query = "select max(id) as max_instructor_id from INSTRUCTORS";
        try (var stat = conn.prepareStatement(query)) {
            ResultSet rs = stat.executeQuery();
            if (rs.next())
                return rs.getInt(1) + 1;
        }
        return 1;
    }


}
