package ru.Korotaev;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.Korotaev.Model.StandardData;
import ru.Korotaev.Model.UserDto;
import ru.Korotaev.service.CheckStandard;

import java.util.Date;

/**
 * Отправитель сообщений
 */
@RestController
@RequestMapping("/msg")
public class MsgController {
    CheckStandard checkStandard = null;
    StandardData standardData;
    //создать пост запрос на добавление стандартов больницы либо выставить дефолтные
    private final KafkaTemplate<Long, UserDto> kafkaTemplate;

    public MsgController(KafkaTemplate<Long, UserDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public void sendOrder(Long msgId, UserDto msg,String pressure,float pulse,float temperature,
                          Long patientId,Long hospitalId){
        Date date = new Date();
        msg.setDate(date.toString());
        msg.setPressure(pressure);
        msg.setPulse(pulse);
        msg.setTemperature(temperature);
        msg.setPatientId(patientId);
        msg.setHospitalId(hospitalId);
        //создадим метод проверки на валидность нормам больницы, если все ок
        //отправляем туда, куда и направлялось
        //если нет - направляем в новую папку с логами типа 'warning'
        //значения по умолчанию заданы в конструкторе StandardDara class
        //обернем в try-catch - вдруг будет ошибка
        try {
            boolean isGoodData = checkStandard.checkStandard(standardData , msg , standardData.getStandardDeviation());
            if(!isGoodData){
                
            }
        }catch (Exception e){
            System.out.println("Error in checking data");
        }
        ListenableFuture<SendResult<Long , UserDto>> future = kafkaTemplate.send("patientDescription",msgId,msg);
        future.addCallback(System.out::println,System.out::println);
        kafkaTemplate.flush();
    }
    @PostMapping("/standard")
    public void saveStandardValue(String normPressure,float normPulse,float normTemperature,int standardDeviation){

            this.standardData.setNormPulse(normPulse);
            this.standardData.setStandardDeviation(standardDeviation);
            this.standardData.setNormPressure(normPressure);
            this.standardData.setNormTemperature(normTemperature);

    }

}
