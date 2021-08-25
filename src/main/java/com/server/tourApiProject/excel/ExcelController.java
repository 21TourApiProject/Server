package com.server.tourApiProject.excel;

import com.server.tourApiProject.touristPoint.area.AreaParams;
import com.server.tourApiProject.touristPoint.area.AreaService;
import com.server.tourApiProject.touristPoint.contentType.ContentTypeParams;
import com.server.tourApiProject.touristPoint.contentType.ContentTypeService;
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
    private final AreaService areaService;
    private final ContentTypeService contentTypeService;

    public ExcelController(TouristDataService touristDataService, AreaService areaService, ContentTypeService contentTypeService) {
        this.touristDataService = touristDataService;
        this.areaService = areaService;
        this.contentTypeService = contentTypeService;
    }

    @GetMapping("/excel")
    public String main() {
        return "excel";
    }

    @PostMapping("/excel/area/read")
    public String readAreaExcel(@RequestParam("file") MultipartFile file, Model model)
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
            AreaParams data = new AreaParams();

            data.setCode1((long) row.getCell(1).getNumericCellValue());
            data.setName1(row.getCell(2).getStringCellValue());
            data.setCode2((long) row.getCell(3).getNumericCellValue());
            data.setName2(row.getCell(4).getStringCellValue());

            areaService.createArea(data);
        }
        System.out.println("엑셀 완료");
        return "excel";
    }

    @PostMapping("/excel/contentType1/read")
    public String readContentType1Excel(@RequestParam("file") MultipartFile file, Model model)
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
            ContentTypeParams data = new ContentTypeParams();

            data.setCode1(row.getCell(1).getStringCellValue());
            data.setName1(row.getCell(2).getStringCellValue());
            data.setCode2(row.getCell(3).getStringCellValue());
            data.setName2(row.getCell(4).getStringCellValue());
            data.setCode3(row.getCell(5).getStringCellValue());
            data.setName3(row.getCell(6).getStringCellValue());

            contentTypeService.createContentType1(data);
        }
        System.out.println("엑셀 완료");
        return "excel";
    }

    @PostMapping("/excel/contentType2/read")
    public String readContentType2Excel(@RequestParam("file") MultipartFile file, Model model)
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
            ContentTypeParams data = new ContentTypeParams();

            data.setCode1(row.getCell(1).getStringCellValue());
            data.setName1(row.getCell(2).getStringCellValue());
            data.setCode2(row.getCell(3).getStringCellValue());
            data.setName2(row.getCell(4).getStringCellValue());
            data.setCode3(row.getCell(5).getStringCellValue());
            data.setName3(row.getCell(6).getStringCellValue());

            contentTypeService.createContentType2(data);
        }
        System.out.println("엑셀 완료");
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
        System.out.println(worksheet.getPhysicalNumberOfRows());
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
            data.setIsCom((int)row.getCell(14).getNumericCellValue());
            data.setIsNear((int)row.getCell(15).getNumericCellValue());
            data.setMapX(row.getCell(16).getNumericCellValue());
            data.setMapY(row.getCell(17).getNumericCellValue());
            data.setOpenTimeFood(row.getCell(18).getStringCellValue());
            data.setOverview(row.getCell(19).getStringCellValue());
            if (!data.getOverview().equals("null")){
                if (data.getOverview().length() > 15)
                    data.setOverviewSim(data.getOverview().substring(0,15)+"..."); //나중에 수정
                else
                    data.setOverviewSim(data.getOverview());
            } else{
                data.setOverviewSim(null);
            }
            data.setPacking(row.getCell(20).getStringCellValue());
            data.setParking(row.getCell(21).getStringCellValue());
            data.setParkingFood(row.getCell(22).getStringCellValue());
            data.setRestDate(row.getCell(23).getStringCellValue());
            data.setRestDateFood(row.getCell(24).getStringCellValue());
            data.setSigunguCode((long) row.getCell(25).getNumericCellValue());
            data.setTel(row.getCell(26).getStringCellValue());
            data.setTitle(row.getCell(27).getStringCellValue());
            data.setTreatMenu(row.getCell(28).getStringCellValue());
            data.setUseTime(row.getCell(29).getStringCellValue());

            touristDataService.createTouristData(data);
        }
        System.out.println("엑셀 완료");
        return "excel";
    }
}
