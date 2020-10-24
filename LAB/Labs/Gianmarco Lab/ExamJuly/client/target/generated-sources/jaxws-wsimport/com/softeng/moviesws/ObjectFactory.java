
package com.softeng.moviesws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.softeng.moviesws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetAllMoviesResponse_QNAME = new QName("http://moviesws.softeng.com/", "getAllMoviesResponse");
    private final static QName _GetAllDirectors_QNAME = new QName("http://moviesws.softeng.com/", "getAllDirectors");
    private final static QName _GetAllDirectorsResponse_QNAME = new QName("http://moviesws.softeng.com/", "getAllDirectorsResponse");
    private final static QName _GetDirector_QNAME = new QName("http://moviesws.softeng.com/", "getDirector");
    private final static QName _GetMovieResponse_QNAME = new QName("http://moviesws.softeng.com/", "getMovieResponse");
    private final static QName _GetDirectorResponse_QNAME = new QName("http://moviesws.softeng.com/", "getDirectorResponse");
    private final static QName _GetMovie_QNAME = new QName("http://moviesws.softeng.com/", "getMovie");
    private final static QName _GetAllMovies_QNAME = new QName("http://moviesws.softeng.com/", "getAllMovies");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.softeng.moviesws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetMovieResponse }
     * 
     */
    public GetMovieResponse createGetMovieResponse() {
        return new GetMovieResponse();
    }

    /**
     * Create an instance of {@link GetAllMoviesResponse }
     * 
     */
    public GetAllMoviesResponse createGetAllMoviesResponse() {
        return new GetAllMoviesResponse();
    }

    /**
     * Create an instance of {@link GetAllDirectorsResponse }
     * 
     */
    public GetAllDirectorsResponse createGetAllDirectorsResponse() {
        return new GetAllDirectorsResponse();
    }

    /**
     * Create an instance of {@link GetDirector }
     * 
     */
    public GetDirector createGetDirector() {
        return new GetDirector();
    }

    /**
     * Create an instance of {@link GetAllDirectors }
     * 
     */
    public GetAllDirectors createGetAllDirectors() {
        return new GetAllDirectors();
    }

    /**
     * Create an instance of {@link GetAllMovies }
     * 
     */
    public GetAllMovies createGetAllMovies() {
        return new GetAllMovies();
    }

    /**
     * Create an instance of {@link GetMovie }
     * 
     */
    public GetMovie createGetMovie() {
        return new GetMovie();
    }

    /**
     * Create an instance of {@link GetDirectorResponse }
     * 
     */
    public GetDirectorResponse createGetDirectorResponse() {
        return new GetDirectorResponse();
    }

    /**
     * Create an instance of {@link Movie }
     * 
     */
    public Movie createMovie() {
        return new Movie();
    }

    /**
     * Create an instance of {@link Director }
     * 
     */
    public Director createDirector() {
        return new Director();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllMoviesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://moviesws.softeng.com/", name = "getAllMoviesResponse")
    public JAXBElement<GetAllMoviesResponse> createGetAllMoviesResponse(GetAllMoviesResponse value) {
        return new JAXBElement<GetAllMoviesResponse>(_GetAllMoviesResponse_QNAME, GetAllMoviesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllDirectors }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://moviesws.softeng.com/", name = "getAllDirectors")
    public JAXBElement<GetAllDirectors> createGetAllDirectors(GetAllDirectors value) {
        return new JAXBElement<GetAllDirectors>(_GetAllDirectors_QNAME, GetAllDirectors.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllDirectorsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://moviesws.softeng.com/", name = "getAllDirectorsResponse")
    public JAXBElement<GetAllDirectorsResponse> createGetAllDirectorsResponse(GetAllDirectorsResponse value) {
        return new JAXBElement<GetAllDirectorsResponse>(_GetAllDirectorsResponse_QNAME, GetAllDirectorsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDirector }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://moviesws.softeng.com/", name = "getDirector")
    public JAXBElement<GetDirector> createGetDirector(GetDirector value) {
        return new JAXBElement<GetDirector>(_GetDirector_QNAME, GetDirector.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMovieResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://moviesws.softeng.com/", name = "getMovieResponse")
    public JAXBElement<GetMovieResponse> createGetMovieResponse(GetMovieResponse value) {
        return new JAXBElement<GetMovieResponse>(_GetMovieResponse_QNAME, GetMovieResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDirectorResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://moviesws.softeng.com/", name = "getDirectorResponse")
    public JAXBElement<GetDirectorResponse> createGetDirectorResponse(GetDirectorResponse value) {
        return new JAXBElement<GetDirectorResponse>(_GetDirectorResponse_QNAME, GetDirectorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMovie }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://moviesws.softeng.com/", name = "getMovie")
    public JAXBElement<GetMovie> createGetMovie(GetMovie value) {
        return new JAXBElement<GetMovie>(_GetMovie_QNAME, GetMovie.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllMovies }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://moviesws.softeng.com/", name = "getAllMovies")
    public JAXBElement<GetAllMovies> createGetAllMovies(GetAllMovies value) {
        return new JAXBElement<GetAllMovies>(_GetAllMovies_QNAME, GetAllMovies.class, null, value);
    }

}
