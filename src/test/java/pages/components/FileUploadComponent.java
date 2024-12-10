package pages.components;

import java.io.File;

import static com.codeborne.selenide.Selenide.$;

public class FileUploadComponent {

    //Отдельный компонент создан заблаговременно на случай появления других инструментов:
    // работа с файлами: превью, трекер загрузки, etc

    public void fileUpload(String fileSelector, String path){
        $(fileSelector).uploadFile(new File(path));

    }
}
