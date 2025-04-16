package com.example.sound.converter;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.time.Duration;

@Converter
public class DurationConverter implements AttributeConverter<Duration, String> {
    @Override
    public String convertToDatabaseColumn(Duration duration) {
        if (duration == null) {
            return null;
        }
        long minutes = duration.toMinutes();
        long seconds = duration.minusMinutes(minutes).getSeconds();
        return String.format("%d:%02d", minutes, seconds);
    }

    @Override
    public Duration convertToEntityAttribute(String durationString) {
        if (durationString == null) {
            return null;
        }
        String[] parts = durationString.split(":");
        long minutes = Long.parseLong(parts[0]);
        long seconds = Long.parseLong(parts[1]);
        return Duration.ofMinutes(minutes).plusSeconds(seconds);
    }
}
