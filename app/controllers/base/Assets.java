package controllers.base;

import play.api.mvc.*;
import controllers.AssetsMetadata;
import play.api.http.HttpErrorHandler;

import javax.inject.Inject;

public class Assets extends controllers.Assets {

    @Inject
    public Assets(HttpErrorHandler errorHandler, AssetsMetadata meta) {
        super(errorHandler, meta);
    }

    public Action<AnyContent> at(String path, String file) {
        boolean aggressiveCaching = true;
        return super.at(path, file, aggressiveCaching);
    }
}