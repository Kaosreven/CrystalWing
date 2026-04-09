package com.github.kaosreven.crystal_wing;

import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Null;
import net.modificationstation.stationapi.api.util.Namespace;

public class ModHelper {

    //public static final Namespace NAMESPACE = Namespace.resolve();
    @Entrypoint.Namespace
    public static Namespace NAMESPACE = Null.get();

    public static class ModHelperFields {
        public static Integer eventCounter = 0;
    }
}
