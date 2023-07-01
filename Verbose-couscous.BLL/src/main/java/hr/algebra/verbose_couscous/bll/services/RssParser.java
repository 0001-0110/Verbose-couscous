package hr.algebra.verbose_couscous.bll.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.events.XMLEvent;

import hr.algebra.verbose_couscous.dal.models.Actor;
import hr.algebra.verbose_couscous.dal.models.Director;
import hr.algebra.verbose_couscous.dal.models.Genre;
import hr.algebra.verbose_couscous.dal.models.Model;
import hr.algebra.verbose_couscous.dal.models.Movie;

/**
 *
 * @author remi
 */
public class RssParser implements IDataDownloader {

    private class MovieBuilder {

        private static IDataService dataService;

        private static final Map<Class<? extends Model>, Map<String, Model>> createdModels = new HashMap<>();

        public static void init(IDataService _dataService) {
            dataService = _dataService;
            createdModels.clear();
            for (Class<? extends Model> type : RELATEDTYPES) {
                createdModels.put(type, new HashMap<>());
            }
        }

        public String Title = null;
        public String Description = null;
        public int Year = -1;
        public int Duration = -1;
        public LocalDateTime PublishedDateTime = null;
        private final Map<Class<? extends Model>, List<Model>> relatedModels = new HashMap<>();

        @SuppressWarnings("unchecked")
        private <T extends Model> T getOrCreate(Class<T> type, String name) throws Exception {
            // Should always contain the key
            /*
             * if (!createdModels.containsKey(type))
             * createdModels.put(type, new HashMap<>());
             */
            if (createdModels.get(type).containsKey(name)) {
                return (T) createdModels.get(type).get(name);
            }
            T model = (T) type.getConstructor(String.class).newInstance(name);
            dataService.insert(type, model);
            createdModels.get(type).put(name, model);
            return model;
        }

        public <T extends Model> void addRelatedModel(Class<T> type, String name) throws Exception {
            T model = getOrCreate(type, name);
            relatedModels.get(type).add(model);
            // Can't build the relation yet, as the movie id can only be known once the
            // movie is inserted
        }

        public boolean isReadyToBuild() {
            return Title != null && Description != null && Year != -1 && Duration != -1 && PublishedDateTime != null;
        }

        public Movie build() {
            Movie movie = new Movie(Title, Description, PublishedDateTime, Year, Duration);
            dataService.insert(Movie.class, movie);
            for (Model genre : relatedModels.get(Genre.class)) {
                dataService.insertMovieGenreRelation(movie, (Genre) genre);
            }
            for (Model actor : relatedModels.get(Actor.class)) {
                dataService.insertMovieActorRelation(movie, (Actor) actor);
            }
            for (Model director : relatedModels.get(Director.class)) {
                dataService.insertMovieDirectorRelation(movie, (Director) director);
            }
            return movie;
        }
    }

    private static final int DEFAULTTIMEOUT = 5000;
    private static final String USERAGENTKEY = "User-Agent";
    private static final String USERAGENTVALUE = "Mozilla/5.0";
    private static final String[] urls = {"https://www.blitz-cinestar-bh.ba/rss.aspx?id=2682",};

    private static final String ITEM = "item";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String YEAR = "godina";
    private static final String DURATION = "trajanje";
    private static final String GENRES = "zanr";
    private static final String DIRECTORS = "redatelj";
    private static final String ACTORS = "glumci";
    private static final String POSTER = "plakat";

    private static final Iterable<Class<? extends Model>> RELATEDTYPES = new ArrayList<Class<? extends Model>>(3) {
        {
            add(Genre.class);
            add(Actor.class);
            add(Director.class);
        }
    };

    private final int timeout;

    private final IDataService dataService;

    public RssParser(IDataService dataService) {
        this(dataService, DEFAULTTIMEOUT);
    }

    public RssParser(IDataService dataService, int timeout) {
        this.dataService = dataService;
        this.timeout = timeout;
    }

    public String[] getUrls() {
        return urls;
    }

    private HttpURLConnection connect(String url, String requestMethod) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setConnectTimeout(timeout);
        connection.setReadTimeout(timeout);
        connection.setRequestMethod(requestMethod);
        connection.addRequestProperty(USERAGENTKEY, USERAGENTVALUE);
        return connection;
    }

    public void downloadData(String url) throws Exception {

        MovieBuilder.init(dataService);
        try (InputStream inputStream = connect(url, "GET").getInputStream()) {
            XMLEventReader reader = XMLInputFactory.newInstance().createXMLEventReader(inputStream);
            String tag = null;
            MovieBuilder movieBuilder = null;
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();

                switch (event.getEventType()) {

                    // Start to parse a new movie
                    case XMLStreamConstants.START_ELEMENT -> {

                        tag = event.asStartElement().getName().getLocalPart();
                        if (tag != null && tag.equals(ITEM)) {
                            if (movieBuilder != null) {
                                movieBuilder.build();
                                movieBuilder = null;
                            }
                            movieBuilder = new MovieBuilder();
                        }
                    }

                    case XMLStreamConstants.CHARACTERS -> {
                        if (tag != null && movieBuilder != null) {
                            String data = event.asCharacters().getData().trim();
                            switch (tag) {
                                case TITLE:
                                    movieBuilder.Title = data;
                                    break;
                                case DESCRIPTION:
                                    movieBuilder.Description = data;
                                    break;
                                case YEAR:
                                    movieBuilder.Year = Integer.valueOf(data);
                                    break;
                                case DURATION:
                                    movieBuilder.Duration = Integer.valueOf(data);
                                    break;
                                case GENRES:
                                    for (String name : data.split(", ")) {
                                        movieBuilder.addRelatedModel(Genre.class, name);
                                    }
                                    break;
                                case ACTORS:
                                    for (String name : data.split(", ")) {
                                        movieBuilder.addRelatedModel(Actor.class, name);
                                    }
                                    break;
                                case DIRECTORS:
                                    for (String name : data.split(", ")) {
                                        movieBuilder.addRelatedModel(Director.class, name);
                                    }
                                    break;
                                case POSTER:
                                    break;
                            }
                        }
                    }
                }
            }
        }
    }
}
