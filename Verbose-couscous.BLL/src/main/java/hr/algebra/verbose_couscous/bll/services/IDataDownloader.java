package hr.algebra.verbose_couscous.bll.services;

/**
 *
 * @author remi
 */
public interface IDataDownloader {

    public String[] getUrls();

    public void downloadData(String url) throws Exception;
}
