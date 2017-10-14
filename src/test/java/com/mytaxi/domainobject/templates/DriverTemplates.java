package com.mytaxi.domainobject.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DriverTemplates implements TemplateLoader
{

    @Override
    public void load()
    {
        Fixture.of(DriverDO.class).addTemplate("driver1", new Rule()
        {{
            add("id", 10L);
            add("dateCreated", ZonedDateTime.of(2017, 01, 01, 01, 00, 00, 00, ZoneId.systemDefault()));
            add("username", "Thor. Odyn's soon");
            add("password", "123456");
            add("deleted", false);
            add("onlineStatus", OnlineStatus.ONLINE);
        }});
        Fixture.of(DriverDO.class).addTemplate("driver2", new Rule()
        {{
            add("id", 20L);
            add("dateCreated", ZonedDateTime.of(2017, 01, 01, 01, 00, 00, 00, ZoneId.systemDefault()));
            add("username", "Spiderman");
            add("password", "654321");
            add("deleted", false);
            add("onlineStatus", OnlineStatus.ONLINE);
            add("selectedCar",has(1).of(CarDO.class,"car3"));
        }});
        Fixture.of(DriverDO.class).addTemplate("driver3", new Rule()
        {{
            add("id", 30L);
            add("dateCreated", ZonedDateTime.of(2017, 02, 01, 01, 00, 00, 00, ZoneId.systemDefault()));
            add("username", "Tony Stark");
            add("password", "456123");
            add("deleted", false);
            add("onlineStatus", OnlineStatus.OFFLINE);
        }});
    }
}
