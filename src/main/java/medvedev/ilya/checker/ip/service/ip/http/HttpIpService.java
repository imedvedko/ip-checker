package medvedev.ilya.checker.ip.service.ip.http;

import lombok.RequiredArgsConstructor;
import medvedev.ilya.checker.ip.service.ip.IpService;
import medvedev.ilya.checker.ip.service.ip.IpServiceException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

@RequiredArgsConstructor
public class HttpIpService implements IpService {
    private final URL url;

    @Override
    public String currentIp() {
        try (final InputStream inputStream = url.openConnection().getInputStream();
             final Reader reader = new InputStreamReader(inputStream);
             final BufferedReader in = new BufferedReader(reader)) {
            return in.readLine();
        } catch (final IOException e) {
            throw new IpServiceException(e);
        }
    }
}
