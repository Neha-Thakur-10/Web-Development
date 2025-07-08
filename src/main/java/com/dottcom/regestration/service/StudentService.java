package com.dottcom.regestration.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dottcom.regestration.model.Student;
import com.dottcom.regestration.repository.StudentRepository;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

@Service
public class StudentService {

    public void exportStudentReport(List<Student> students, HttpServletResponse response) throws Exception {
        // Define styling for report
        StyleBuilder boldStyle = stl.style().bold();
        StyleBuilder columnTitleStyle = stl.style(boldStyle)
            .setBackgroundColor(java.awt.Color.LIGHT_GRAY)
            .setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)
            .setBorder(stl.pen1Point());

        StyleBuilder centerStyle = stl.style()
            .setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)
            .setBorder(stl.pen1Point())
            .setPadding(2);

        // Optional: Deep copy (if needed for transformation or isolation)
        List<Student> convertedList = new ArrayList<>();
        for (Student student : students) {
            Student copy = new Student();
            copy.setId(student.getId());
            copy.setFirstName(student.getFirstName());
            copy.setMiddleName(student.getMiddleName());
            copy.setLastName(student.getLastName());
            copy.setDob(student.getDob());
            copy.setGender(student.getGender());
            copy.setContact(student.getContact());
            copy.setAddress(student.getAddress());
            copy.setEducation(student.getEducation());
            convertedList.add(copy);
        }

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(convertedList);

        // Define columns
        TextColumnBuilder<Integer> srNoCol = col.reportRowNumberColumn("Sr No.").setStyle(centerStyle).setFixedColumns(6);
        TextColumnBuilder<String> fullNameCol = col.column("Full Name", "fullName", type.stringType()).setStyle(centerStyle);
        TextColumnBuilder<Date> dobCol = col.column("Date of Birth", "dob", type.dateType()).setStyle(centerStyle);
        TextColumnBuilder<String> genderCol = col.column("Gender", "gender", type.stringType()).setStyle(centerStyle);
        TextColumnBuilder<String> contactCol = col.column("Contact No.", "contact", type.stringType()).setStyle(centerStyle);
        TextColumnBuilder<String> addressCol = col.column("Address", "address", type.stringType()).setStyle(centerStyle);
        TextColumnBuilder<String> educationCol = col.column("Education", "education", type.stringType()).setStyle(centerStyle);

        // Build report layout
        JasperReportBuilder report = report()
            .setPageFormat(PageType.A4)
            .setColumnTitleStyle(columnTitleStyle)
            .columns(srNoCol, fullNameCol, dobCol, genderCol, contactCol, addressCol, educationCol)
            .title(cmp.text("Student Report").setStyle(boldStyle.setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)))
            .highlightDetailEvenRows()
            .pageFooter(cmp.pageXofY())
            .setDataSource(dataSource);

        // Set response headers
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=students.pdf");

        // Export as PDF
        report.toPdf(response.getOutputStream());
    }
    @Autowired
    private StudentRepository studentRepository;
	public void save(@Valid Student student) {
		// TODO Auto-generated method stub
		studentRepository.save(student);
    
	}
}
