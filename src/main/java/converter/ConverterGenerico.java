package converter;

import facade.AbstractFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class ConverterGenerico implements Converter {

    private final AbstractFacade facade;

    public ConverterGenerico(AbstractFacade facade) {
        this.facade = facade;
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string.equals("null")) {
            return null;
        }
        return facade.pesquisar(Long.valueOf(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return o.toString();
    }

}
