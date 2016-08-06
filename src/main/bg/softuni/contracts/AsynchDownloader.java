package main.bg.softuni.contracts;

public interface AsynchDownloader extends Downloader{

    public void downloadOnNewThread(String fileUrl);
}
