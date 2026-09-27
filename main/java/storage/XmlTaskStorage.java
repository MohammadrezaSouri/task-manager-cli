package storage;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import model.Task;

import java.io.File;
import java.util.List;

public class XmlTaskStorage {

    public static void saveTasks(List<Task> tasks, String filePath) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(TaskListWrapper.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        TaskListWrapper wrapper = new TaskListWrapper();
        wrapper.setTasks(tasks);

        marshaller.marshal(wrapper, new File(filePath));
    }
    public static List<Task> loadTasks(String filePath) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(TaskListWrapper.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        TaskListWrapper wrapper = (TaskListWrapper) unmarshaller.unmarshal(new File(filePath));
        return wrapper.getTasks();
    }
}
