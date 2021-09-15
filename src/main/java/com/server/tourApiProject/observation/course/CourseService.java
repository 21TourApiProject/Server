package com.server.tourApiProject.observation.course;

import com.server.tourApiProject.observation.Observation;
import com.server.tourApiProject.observation.ObservationRepository;
import com.server.tourApiProject.touristPoint.contentType.ContentTypeRepository;
import com.server.tourApiProject.touristPoint.touristData.TouristData;
import com.server.tourApiProject.touristPoint.touristData.TouristDataCourseParams;
import com.server.tourApiProject.touristPoint.touristData.TouristDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final TouristDataRepository touristDataRepository;
    private final ContentTypeRepository contentTypeRepository;
    private final ObservationRepository observationRepository;

    public List<TouristDataCourseParams> getCourseTPList(Long observationId) {
        //코스 받아서 순서대로 정리후 관광지Id 리스트반환

        List<Course> courseList= courseRepository.findByObservationId(observationId);
        Collections.sort(courseList, new CourseOrderComparator());

        List<TouristDataCourseParams> courseTPList = new ArrayList<>();
        for (Course p : courseList) {
            courseTPList.add(getCourseTouristPointData(p.getTouristPointId()));
        }

        return courseTPList;
    }

    public List<String> getCourseNameList(Long observationId) {
        //코스 받아서 관측지와 합쳐서 순서대로 이름제공 (이름인디케이터에 이용)
        List<TouristDataCourseParams> tpList = getCourseTPList(observationId);
        Observation observation = observationRepository.findById(observationId).orElseThrow(IllegalAccessError::new);
        List<String> nameList = new ArrayList<>();

        for(TouristDataCourseParams tp :tpList)
        {
            nameList.add(tp.getTitle());
        }
        nameList.add(observation.getCourseOrder(), observation.getObservationName());

        return nameList;
    }

    public TouristDataCourseParams getCourseTouristPointData(Long contentId) {
        TouristData touristData = touristDataRepository.findByContentId(contentId);
        TouristDataCourseParams result = new TouristDataCourseParams();

        result.setContentTypeId(touristData.getContentTypeId()); //12
        result.setFirstImage(touristData.getFirstImage());
        result.setTitle(touristData.getTitle());
        result.setOverview(touristData.getOverview());
        result.setAddr(touristData.getAddr());


        if (touristData.getContentTypeId() == 12)//관광지
        {
            result.setUseTime(touristData.getUseTime());
            result.setParking(touristData.getParking());
            result.setCat3Name(contentTypeRepository.findByCat3Code(touristData.getCat3()).getCat3Name());
        } else {
            result.setCat3Name(contentTypeRepository.findByCat3Code(touristData.getCat3()).getCat3Name());
            result.setUseTime(touristData.getOpenTimeFood());
            result.setTreatMenu(touristData.getTreatMenu());
            result.setParking(touristData.getParkingFood());
        }

        return result;
    }


    class CourseOrderComparator implements Comparator<Course> {
        //코스 순서대로 오름차순 정리
        @Override
        public int compare(Course o1, Course o2) {
            if (o1.getCourseOrder() > o2.getCourseOrder()) {
                return 1;
            } else if (o1.getCourseOrder() < o2.getCourseOrder()) {
                return -1;
            }
            return 0;
        }
    }

}
