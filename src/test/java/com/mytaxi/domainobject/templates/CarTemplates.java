package com.mytaxi.domainobject.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.mytaxi.domainobject.CarDO;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class CarTemplates implements TemplateLoader
{
    @Override
    public void load()
    {
        Fixture.of(CarDO.class).addTemplate("car1",new Rule(){{
            add("id",50L);
            add("dateCreated", ZonedDateTime.of(2017, 01, 01, 01, 00, 00, 00, ZoneId.systemDefault()));
            add("licensePlate","XDD685");
            add("seatCount",4);
            add("convertible",false);
            add("rating","9.0");
            add("engineType","GAS");
            add("manufacturer","Audi");
        }});
        Fixture.of(CarDO.class).addTemplate("car2",new Rule(){{
            add("id",60L);
            add("dateCreated", ZonedDateTime.of(2017, 01, 01, 01, 00, 00, 00, ZoneId.systemDefault()));
            add("licensePlate","EFF365");
            add("seatCount",4);
            add("convertible",false);
            add("rating","9.5");
            add("engineType", "GAS");
            add("manufacturer","BMW");
        }});
        Fixture.of(CarDO.class).addTemplate("car3",new Rule(){{
            add("id",70L);
            add("dateCreated", ZonedDateTime.of(2017, 01, 01, 01, 00, 00, 00, ZoneId.systemDefault()));
            add("licensePlate","XZD523");
            add("seatCount",4);
            add("convertible",false);
            add("rating","8.7");
            add("engineType","ELECTRIC");
            add("manufacturer","TOYOTA");
        }});
    }
}
