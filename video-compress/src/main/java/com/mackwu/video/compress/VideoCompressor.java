package com.mackwu.video.compress;

/**
 * @author MackWu
 * @since 2023/2/3 18:40
 */
public interface VideoCompressor {

    void startCompress(CompressListener compressListener);

    void cancelCompress();

    interface CompressListener {

        void onCompressProgressChanged(int progress);

        void onCompressComplete();

        void onCompressFail();
    }

    class Builder {
        String inPath;
        String outPath;
        String width;
        String height;

        public Builder inPath(String inPath) {
            this.inPath = inPath;
            return this;
        }

        public Builder outPath(String outPath) {
            this.outPath = outPath;
            return this;
        }

        public Builder width(String width) {
            this.width = width;
            return this;
        }

        public Builder height(String height) {
            this.height = height;
            return this;
        }

        public VideoCompressor build() {
            return new FFmpegKitCompressor(this);
        }
    }

}
