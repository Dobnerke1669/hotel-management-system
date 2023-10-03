import React, { useState, useEffect } from 'react';

function PhotoComponent() {
    const [photoUrl, setPhotoUrl] = useState('');

    useEffect(() => {
        const id = 2;
        // Replace 'YOUR_ENDPOINT_URL' with the actual URL of your endpoint.
        fetch(`http://localhost:8080/image/${id}`)
            .then((response) => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                // Assuming the response contains the image data as a blob.
                return response.blob();
            })
            .then((blob) => {
                // Create a blob URL for the image.
                const imageUrl = URL.createObjectURL(blob);
                setPhotoUrl(imageUrl);
            })
            .catch((error) => {
                console.error('Error fetching image:', error);
            });
    }, []);

    return (
        <div>
            {photoUrl && <img src={photoUrl} alt="Fetched Photo" />}
        </div>
    );
}

export default PhotoComponent;
