package com.server.tourApiProject.excel;

import com.server.tourApiProject.touristPoint.touristData.TouristData;
import com.server.tourApiProject.touristPoint.touristData.TouristDataService;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

//일단 readTouristDataExcel함수 복사 붙여넣기하고 함수명 수정, 아까 action에 쓴 url로 수정 그리고 for문 안에 내용 수정하면 됨
@Controller
public class ExcelController {
    private final TouristDataService touristDataService;

    public ExcelController(TouristDataService touristDataService) {
        this.touristDataService = touristDataService;
    }

    @GetMapping("/excel")
    public String main() {
        return "excel";
    }


    @PostMapping("/excel/touristData/read")
    public String readTouristDataExcel(@RequestParam("file") MultipartFile file, Model model)
            throws IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("엑셀파일만 업로드 해주세요.");
        }
        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = workbook.getSheetAt(0);
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            Row row = worksheet.getRow(i);
            TouristData data = new TouristData();

            data.setContentId((long) row.getCell(0).getNumericCellValue());
            data.setAddr1(row.getCell(1).getStringCellValue());
            data.setAddr2(row.getCell(2).getStringCellValue());
            data.setAreaCode((long) row.getCell(3).getNumericCellValue());
            data.setCat1(row.getCell(4).getStringCellValue());
            data.setCat2(row.getCell(5).getStringCellValue());
            data.setCat3(row.getCell(6).getStringCellValue());
            data.setChkPet(row.getCell(7).getStringCellValue());
            data.setContentTypeId((long) row.getCell(8).getNumericCellValue());
            data.setExpGuide(row.getCell(9).getStringCellValue());
            data.setFirstImage(row.getCell(10).getStringCellValue());
            data.setFirstImage2(row.getCell(11).getStringCellValue());
            data.setFirstMenu(row.getCell(12).getStringCellValue());
            data.setHomePage(row.getCell(13).getStringCellValue());
            data.setMapX((double) row.getCell(14).getNumericCellValue());
            data.setMapY((double) row.getCell(15).getNumericCellValue());
            data.setOpenTimeFood(row.getCell(16).getStringCellValue());
            data.setOverview(row.getCell(17).getStringCellValue());
            data.setPacking(row.getCell(18).getStringCellValue());
            data.setParking(row.getCell(19).getStringCellValue());
            data.setParkingFood(row.getCell(20).getStringCellValue());
            data.setRestDate(row.getCell(21).getStringCellValue());
            data.setRestDateFood(row.getCell(22).getStringCellValue());
            data.setSigunguCode((long) row.getCell(23).getNumericCellValue());
            data.setTel(row.getCell(24).getStringCellValue());
            data.setTitle(row.getCell(25).getStringCellValue());
            data.setTreatMenu(row.getCell(26).getStringCellValue());
            data.setUseTime(row.getCell(27).getStringCellValue());

            touristDataService.createTouristData(data);
        }
        return "excel";
    }
}
