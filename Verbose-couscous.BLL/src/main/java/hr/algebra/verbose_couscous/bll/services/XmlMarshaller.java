package hr.algebra.verbose_couscous.bll.services;

import java.io.File;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author remi
 */
public class XmlMarshaller implements IMarshaller {

    private Optional<JAXBContext> getContext(Class<?> type) {

        try {
            return Optional.of(JAXBContext.newInstance(type));
        } catch (JAXBException ex) {
            return Optional.empty();
        }
    }

    @SuppressWarnings("unchecked")
    public <T> Optional<T> importFrom(String filename, Class<T> type) {

        Optional<JAXBContext> context = getContext(type);
        if (context.isEmpty())
            return Optional.empty();
        T result;
        try {
            Unmarshaller unmarshaller = context.get().createUnmarshaller();
            result = (T) unmarshaller.unmarshal(new File(filename));
        } catch (JAXBException | ClassCastException discarded) {
            return Optional.empty();
        }
        return Optional.ofNullable(result);
    }

    public <T> boolean exportTo(String filename, T model) {

        Optional<JAXBContext> context = getContext(model.getClass());
        if (context.isEmpty())
            return false;
        try {
            Marshaller marshaller = context.get().createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(model, new File(filename));
        } catch (JAXBException discarded) {
            return false;
        }
        return true;
    }
}
