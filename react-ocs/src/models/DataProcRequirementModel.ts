export class DataProcRequirementModel {
    fileType: string;
    fileQuality: string;
    colorType: string;
    contrast: number;
    brightness: number;
    saturation: number;
    highlights: number;
    exposure: number;
    shadows: number;
    whites: number;
    blacks: number;
    luminance: number;
    hue: number;
  
    constructor(fileType: string, fileQuality: string,
      colorType: string,
      contrast: number,
      brightness: number,
      saturation: number,
      highlights: number,
      exposure: number,
      shadows: number,
      whites: number,
      blacks: number,
      luminance: number,
      hue: number,
    ) {
      this.fileType = fileType;
      this.fileQuality = fileQuality;
      this.colorType = colorType;
      this.contrast = contrast;
      this.brightness = brightness;
      this.saturation = saturation;
      this.highlights = highlights;
      this.exposure = exposure;
      this.shadows = shadows;
      this.whites = whites;
      this.blacks = blacks;
      this.luminance = luminance;
      this.hue = hue;
    }
  }

export default DataProcRequirementModel;