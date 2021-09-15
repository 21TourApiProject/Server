package com.server.tourApiProject.excel;

import com.server.tourApiProject.observation.Observation;
import com.server.tourApiProject.observation.ObservationRepository;
import com.server.tourApiProject.observation.observeFee.ObserveFee;
import com.server.tourApiProject.observation.observeFee.ObserveFeeRepository;
import com.server.tourApiProject.observation.observeHashTag.ObserveHashTag;
import com.server.tourApiProject.observation.observeHashTag.ObserveHashTagRepository;
import com.server.tourApiProject.observation.observeImage.ObserveImage;
import com.server.tourApiProject.observation.observeImage.ObserveImageRepository;
import com.server.tourApiProject.touristPoint.area.AreaParams;
import com.server.tourApiProject.touristPoint.area.AreaService;
import com.server.tourApiProject.touristPoint.contentType.ContentTypeParams;
import com.server.tourApiProject.touristPoint.contentType.ContentTypeService;
import com.server.tourApiProject.touristPoint.nearTouristData.NearTouristData;
import com.server.tourApiProject.touristPoint.nearTouristData.NearTouristDataRepository;
import com.server.tourApiProject.touristPoint.touristData.TouristData;
import com.server.tourApiProject.touristPoint.touristData.TouristDataService;
import com.server.tourApiProject.touristPoint.touristDataHashTag.TouristDataHashTag;
import com.server.tourApiProject.touristPoint.touristDataHashTag.TouristDataHashTagRepository;
import com.server.tourApiProject.weather.WtArea;
import com.server.tourApiProject.weather.WtAreaRepository;
import com.server.tourApiProject.weather.WtAreaService;
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
    private final NearTouristDataRepository nearTouristDataRepository;
    private final TouristDataHashTagRepository touristDataHashTagRepository;
    private final WtAreaRepository wtAreaRepository;
    private final ObservationRepository observationRepository;
    private final ObserveHashTagRepository observeHashTagRepository;
    private final ObserveImageRepository observeImageRepository;
    private final ObserveFeeRepository observeFeeRepository;

    public ExcelController(TouristDataService touristDataService, AreaService areaService, ContentTypeService contentTypeService, NearTouristDataRepository nearTouristDataRepository, TouristDataHashTagRepository touristDataHashTagRepository, WtAreaService wtAreaService, WtAreaRepository wtAreaRepository, ObservationRepository observationRepository, ObserveHashTagRepository observeHashTagRepository, ObserveImageRepository observeImageRepository, ObserveFeeRepository observeFeeRepository) {
        this.touristDataService = touristDataService;
        this.areaService = areaService;
        this.contentTypeService = contentTypeService;
        this.nearTouristDataRepository = nearTouristDataRepository;
        this.touristDataHashTagRepository = touristDataHashTagRepository;
        this.wtAreaRepository = wtAreaRepository;
        this.observationRepository = observationRepository;
        this.observeHashTagRepository = observeHashTagRepository;
        this.observeImageRepository = observeImageRepository;
        this.observeFeeRepository = observeFeeRepository;
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
            data.setAddr(row.getCell(1).getStringCellValue());
            if (data.getAddr().equals("null"))
                data.setAddr(null);
            data.setAreaCode((long) row.getCell(2).getNumericCellValue());
            data.setCat1(row.getCell(3).getStringCellValue());
            if (data.getCat1().equals("null"))
                data.setCat1(null);
            data.setCat2(row.getCell(4).getStringCellValue());
            if (data.getCat2().equals("null"))
                data.setCat2(null);
            data.setCat3(row.getCell(5).getStringCellValue());
            if (data.getCat3().equals("null"))
                data.setCat3(null);
            data.setChkPet(row.getCell(6).getStringCellValue());
            if (data.getChkPet().equals("null"))
                data.setChkPet(null);
            data.setContentTypeId((long) row.getCell(7).getNumericCellValue());
            data.setExpGuide(row.getCell(8).getStringCellValue());
            if (data.getExpGuide().equals("null"))
                data.setExpGuide(null);
            data.setFirstImage(row.getCell(9).getStringCellValue());
            if (data.getFirstImage().equals("null"))
                data.setFirstImage(null);
            data.setFirstMenu(row.getCell(10).getStringCellValue());
            if (data.getFirstMenu().equals("null"))
                data.setFirstMenu(null);
            data.setHomePage(row.getCell(11).getStringCellValue());
            if (data.getHomePage().equals("null"))
                data.setHomePage(null);
            data.setIsCom((int) row.getCell(12).getNumericCellValue());
            data.setIsIm((int) row.getCell(13).getNumericCellValue());
            data.setIsJu((int) row.getCell(14).getNumericCellValue());
            data.setMapX(row.getCell(15).getNumericCellValue());
            data.setMapY(row.getCell(16).getNumericCellValue());
            data.setOpenTimeFood(row.getCell(17).getStringCellValue());
            if (data.getOpenTimeFood().equals("null"))
                data.setOpenTimeFood(null);
            data.setOverview(row.getCell(18).getStringCellValue());
            if (data.getOverview().equals("null"))
                data.setOverview(null);
            data.setOverviewSim(row.getCell(19).getStringCellValue());
            if (data.getOverviewSim().equals("null"))
                data.setOverviewSim(null);
            data.setPacking(row.getCell(20).getStringCellValue());
            if (data.getPacking().equals("null"))
                data.setPacking(null);
            data.setParking(row.getCell(21).getStringCellValue());
            if (data.getParking().equals("null"))
                data.setParking(null);
            data.setParkingFood(row.getCell(22).getStringCellValue());
            if (data.getParkingFood().equals("null"))
                data.setParkingFood(null);
            data.setRestDate(row.getCell(23).getStringCellValue());
            if (data.getRestDate().equals("null"))
                data.setRestDate(null);
            data.setRestDateFood(row.getCell(24).getStringCellValue());
            if (data.getRestDateFood().equals("null"))
                data.setRestDateFood(null);
            data.setSigunguCode((long) row.getCell(25).getNumericCellValue());
            data.setTel(row.getCell(26).getStringCellValue());
            if (data.getTel().equals("null"))
                data.setTel(null);
            data.setTitle(row.getCell(27).getStringCellValue());
            if (data.getTitle().equals("null"))
                data.setTitle(null);
            data.setTreatMenu(row.getCell(28).getStringCellValue());
            if (data.getTreatMenu().equals("null"))
                data.setTreatMenu(null);
            data.setUseTime(row.getCell(29).getStringCellValue());
            if (data.getUseTime().equals("null"))
                data.setUseTime(null);

            touristDataService.createTouristData(data);
        }
        System.out.println("엑셀 완료");
        return "excel";
    }

    @PostMapping("/excel/nearTouristData/read")
    public String readnearTouristDataExcel(@RequestParam("file") MultipartFile file, Model model)
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
            NearTouristData data = new NearTouristData();

            data.setNearTouristDataId((long) row.getCell(0).getNumericCellValue());
            data.setAddr1(row.getCell(1).getStringCellValue());
            if (data.getAddr1().equals("null"))
                data.setAddr1(null);
            data.setCat3Name(row.getCell(2).getStringCellValue());
            if (data.getCat3Name().equals("null"))
                data.setCat3Name(null);
            data.setContentId((long) row.getCell(3).getNumericCellValue());
            data.setFirstImage(row.getCell(4).getStringCellValue());
            if (data.getFirstImage().equals("null"))
                data.setFirstImage(null);
            data.setOverviewSim(row.getCell(5).getStringCellValue());
            if (data.getOverviewSim().equals("null"))
                data.setOverviewSim(null);
            data.setTitle(row.getCell(6).getStringCellValue());
            if (data.getTitle().equals("null"))
                data.setTitle(null);
            data.setTouristDataId((long) row.getCell(7).getNumericCellValue());

            nearTouristDataRepository.save(data);
        }
        System.out.println("엑셀 완료");
        return "excel";
    }

    @PostMapping("/excel/touristDataHashTag/read")
    public String readTouristDataHashTagExcel(@RequestParam("file") MultipartFile file, Model model)
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
            TouristDataHashTag data = new TouristDataHashTag();

            data.setTouristDataHashTagId((long) row.getCell(0).getNumericCellValue());
            data.setContentId((long) row.getCell(1).getNumericCellValue());
            data.setHashTagId((long) row.getCell(2).getNumericCellValue());
            data.setHashTagName(row.getCell(3).getStringCellValue());

            touristDataHashTagRepository.save(data);
        }
        System.out.println("엑셀 완료");
        return "excel";
    }

    @PostMapping("/excel/wtArea/read")
    public String readWtAreaExcel(@RequestParam("file") MultipartFile file, Model model)
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
            WtArea data = new WtArea();

            data.setWtAreaId((long) row.getCell(0).getNumericCellValue());
            data.setCityName(row.getCell(1).getStringCellValue());
            System.out.println(row.getCell(1).getStringCellValue());
            data.setProvName(row.getCell(2).getStringCellValue());
            data.setLatitude(row.getCell(3).getNumericCellValue());
            data.setLongitude(row.getCell(4).getNumericCellValue());
            data.setMinLightPol(row.getCell(5).getNumericCellValue());
            data.setMaxLightPol(row.getCell(6).getNumericCellValue());

            wtAreaRepository.save(data);
        }
        System.out.println("엑셀 완료");
        return "excel";
    }

    @PostMapping("/excel/observationData/read")
    public String readObservationDataExcel(@RequestParam("file") MultipartFile file, Model model)
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
            Observation data = new Observation();
            if(row.getCell(0) == null){
                break;
            }
            data.setObservationId((long) row.getCell(0).getNumericCellValue());
            data.setObservationName(row.getCell(1).getStringCellValue());
            data.setOutline(row.getCell(2).getStringCellValue());
            data.setIntro(row.getCell(3).getStringCellValue());
            data.setAddress(row.getCell(4).getStringCellValue());
            data.setPhoneNumber(row.getCell(5).getStringCellValue());
            if (data.getPhoneNumber().equals("null"))
                data.setPhoneNumber(null);
            data.setOperatingHour(row.getCell(6).getStringCellValue());
            if (data.getOperatingHour().equals("null"))
                data.setOperatingHour(null);
            data.setClosedDay(row.getCell(7).getStringCellValue());
            if (data.getClosedDay().equals("null"))
                data.setClosedDay(null);
            data.setGuide(row.getCell(8).getStringCellValue());
            data.setParking(row.getCell(9).getStringCellValue());
            data.setLink(row.getCell(10).getStringCellValue());
            if (data.getLink().equals("null"))
                data.setLink(null);
            data.setObserveType(row.getCell(11).getStringCellValue());
            data.setLatitude((double)row.getCell(12).getNumericCellValue());
            data.setLongitude((double)row.getCell(13).getNumericCellValue());
            data.setLight((double)row.getCell(14).getNumericCellValue());
            if(row.getCell(15).getNumericCellValue()==1)
                data.setNature(true);
            else
                data.setNature(false);

            observationRepository.save(data);
        }
        System.out.println("엑셀 완료");
        return "excel";
    }
    @PostMapping("/excel/observationHashTag/read")
    public String readObservationHashTagExcel(@RequestParam("file") MultipartFile file, Model model)
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
            if(row.getCell(0) == null){
                break;
            }
            ObserveHashTag data = new ObserveHashTag();

            data.setObserveHashTagListId((long) row.getCell(0).getNumericCellValue());
            data.setObservationId((long) row.getCell(1).getNumericCellValue());
            data.setHashTagId((long) row.getCell(2).getNumericCellValue());
            data.setHashTagName(row.getCell(3).getStringCellValue());

            observeHashTagRepository.save(data);
        }
        System.out.println("엑셀 완료");
        return "excel";
    }

    @PostMapping("/excel/observationFee/read")
    public String readObservationFeeExcel(@RequestParam("file") MultipartFile file, Model model)
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
            if(row.getCell(0) == null){
                break;
            }
            ObserveFee data = new ObserveFee();

            data.setObserveFeeListId((long) row.getCell(0).getNumericCellValue());
            data.setObservationId((long) row.getCell(1).getNumericCellValue());
            data.setFeeName(row.getCell(2).getStringCellValue());
            data.setEntranceFee(row.getCell(3).getStringCellValue());
            if(data.getEntranceFee().equals("null"))
                data.setEntranceFee(null);

            observeFeeRepository.save(data);
        }
        System.out.println("엑셀 완료");
        return "excel";
    }

    @PostMapping("/excel/observationImage/read")
    public String readObservationImageExcel(@RequestParam("file") MultipartFile file, Model model)
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
            if(row.getCell(0) == null){
                break;
            } else if (row.getCell(2)==null) {
                continue;
            }
            ObserveImage data = new ObserveImage();

            data.setObserveImageListId((long) row.getCell(0).getNumericCellValue());
            data.setObservationId((long) row.getCell(1).getNumericCellValue());
            data.setImage(row.getCell(2).getStringCellValue());
            data.setImageSource(row.getCell(3).getStringCellValue());

            observeImageRepository.save(data);
        }
        System.out.println("엑셀 완료");
        return "excel";
    }

}
