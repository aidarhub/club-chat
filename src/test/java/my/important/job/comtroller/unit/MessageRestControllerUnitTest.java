package my.important.job.comtroller.unit;

import my.important.job.controller.MessageRestController;
import my.important.job.dto.MessageCreateDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class MessageRestControllerUnitTest {

    @Test
    void saveTest() {
        final var messageRestController = new MessageRestController();
        messageRestController.save(new MessageCreateDto("text", 1, 2, LocalDate.now()));
        Assertions.assertNotNull(messageRestController.findById(0));
    }
}
