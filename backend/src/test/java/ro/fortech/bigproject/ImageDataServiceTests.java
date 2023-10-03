package ro.fortech.bigproject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ro.fortech.project.entities.ImageData;
import ro.fortech.project.repository.ImageDataRepository;
import ro.fortech.project.services.ImageDataService;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class ImageDataServiceTests {

    @InjectMocks
    private ImageDataService imageDataService;

    @Mock
    private ImageDataRepository imageDataRepository;

    @Test
    public void testGetImage(){
        Long imageId = 1L;
        ImageData expectedImageData = new ImageData();
        when(imageDataRepository.findById(imageId)).thenReturn(Optional.of(expectedImageData));
        ImageData resultImageData = imageDataService.getImage(imageId);
        verify(imageDataRepository, times(1)).findById(imageId);
        assertNotNull(resultImageData);
    }


}