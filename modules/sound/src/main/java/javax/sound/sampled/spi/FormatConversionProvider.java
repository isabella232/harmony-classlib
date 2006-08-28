/*
 *  Copyright 2006 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package javax.sound.sampled.spi;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;

public abstract class FormatConversionProvider {

    public abstract AudioInputStream getAudioInputStream(
            AudioFormat.Encoding targetEncoding, AudioInputStream sourceStream);

    public abstract AudioInputStream getAudioInputStream(
            AudioFormat targetFormat, AudioInputStream sourceStream);

    public abstract AudioFormat.Encoding[] getTargetEncodings(
            AudioFormat sourceFormat);

    public boolean isConversionSupported(AudioFormat.Encoding targetEncoding,
            AudioFormat sourceFormat) {
        AudioFormat.Encoding[] encodings = getTargetEncodings(sourceFormat);
        for (int i = 0; i < encodings.length; i++) {
            if (targetEncoding.equals(encodings[i])) {
                return true;
            }
        }
        return false;
    }

    public abstract AudioFormat[] getTargetFormats(
            AudioFormat.Encoding targetFormat, AudioFormat sourceFormat);

    public boolean isConversionSupported(AudioFormat targetFormat,
            AudioFormat sourceFormat) {
        AudioFormat[] formats = getTargetFormats(targetFormat.getEncoding(),
                sourceFormat);
        for (int i = 0; i < formats.length; i++) {
            if (targetFormat.equals(formats[i])) {
                return true;
            }
        }
        return false;
    }

    public abstract AudioFormat.Encoding[] getSourceEncodings();

    public boolean isSourceEncodingSupported(AudioFormat.Encoding sourceEncoding) {
        AudioFormat.Encoding[] encodings = getSourceEncodings();
        for (int i = 0; i < encodings.length; i++) {
            if (sourceEncoding.equals(encodings[i])) {
                return true;
            }
        }
        return false;
    }

    public abstract AudioFormat.Encoding[] getTargetEncodings();

    public boolean isTargetEncodingSupported(AudioFormat.Encoding targetEncoding) {
        AudioFormat.Encoding[] encodings = getTargetEncodings();
        for (int i = 0; i < encodings.length; i++) {
            if (targetEncoding.equals(encodings[i])) {
                return true;
            }
        }
        return false;
    }

}
