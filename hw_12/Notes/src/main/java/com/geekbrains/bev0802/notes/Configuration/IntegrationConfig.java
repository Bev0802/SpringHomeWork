package com.geekbrains.bev0802.notes.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.io.File;

/**
 * Конфигурационный класс для Spring Integration.
 * Определяет компоненты для обработки сообщений, включая каналы и обработчики.
 * @Configuration -  указывает, что класс является конфигурационным и содержит определения
 * и зависимости bean'ов.
 */
@Configuration
public class IntegrationConfig {

    /**
     * Определяет канал сообщений для входящего текста.
     *
     * @return Новый прямой канал для передачи текстовых сообщений.
     */
    @Bean
    public MessageChannel textInputChanel() {
        return new DirectChannel();
    }

    /**
     * Определяет канал сообщений для обработчика записи в файл.
     *
     * @return Новый прямой канал для передачи текстовых сообщений к обработчику файлов.
     *
     * DirectChannel - предоставляет механизм для непосредственной отправки сообщений между компонентами
     * без буферизации или переупорядочения.
     */
    @Bean
    public MessageChannel fileWriterChanel() {
        return new DirectChannel();
    }
    /**
     * Данный метод определяет трансформер, который принимает текст из textInputChannel и передает его в fileWriterChannel.
     * В данной реализации трансформер просто передает текст без изменений.
     *
     * @return Трансформер, преобразующий текст для дальнейшей обработки.
     *
     * @Transformer - аннотация для указания компонента, который преобразует сообщения из одного формата в другой.
     * В вашем случае, он не изменяет сообщение, но может быть расширен для выполнения любой логики преобразования.     *
     */
    @Bean
    @Transformer(inputChannel = "textInputChanel", outputChannel = "fileWriterChanel")
    public GenericTransformer<String, String> mainTransformer() {
        return text -> {
            // Здесь может быть реализована любая логика преобразования текста.
            return text;
        };
    }

    /**
     * Определяет обработчик сообщений, который записывает текст в файл.
     * Конфигурирует обработчик для добавления текста в существующий файл или создания нового, если он не существует.
     *
     * @return Обработчик сообщений для записи текста в файл.
     *
     * @ServiceActivator - указывает на метод или класс, который будет обрабатывать сообщения,
     */    
    @Bean
    @ServiceActivator(inputChannel = "fileWriterChanel")
    public FileWritingMessageHandler fileWritingMessageHandler() {
        File directory = new File("./src/main/resources/files");
        if (!directory.exists()) {
            boolean wasCreated = directory.mkdirs();
            if (!wasCreated) {
                throw new RuntimeException("Не удалось создать директорию для файлов: " + directory.getAbsolutePath());
            }
        }
        FileWritingMessageHandler handler = 
                new FileWritingMessageHandler(directory);
        handler.setExpectReply(false); // Не ожидает ответа после обработки сообщения.
        handler.setFileExistsMode(FileExistsMode.APPEND); // Добавляет текст к существующему файлу.
        handler.setAppendNewLine(true); // Добавляет новую строку после каждого сообщения.
        
        return handler;
    }
}

