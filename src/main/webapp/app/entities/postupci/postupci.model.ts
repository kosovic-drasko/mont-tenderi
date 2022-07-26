import dayjs from 'dayjs/esm';
import { IPonude } from 'app/entities/ponude/ponude.model';

export interface IPostupci {
  id?: number;
  sifraPostupka?: number;
  brojTendera?: string;
  opisPostupka?: string | null;
  vrstaPostupka?: string;
  datumObjave?: dayjs.Dayjs | null;
  datumOtvaranja?: dayjs.Dayjs | null;
  ponudes?: IPonude[] | null;
}

export class Postupci implements IPostupci {
  constructor(
    public id?: number,
    public sifraPostupka?: number,
    public brojTendera?: string,
    public opisPostupka?: string | null,
    public vrstaPostupka?: string,
    public datumObjave?: dayjs.Dayjs | null,
    public datumOtvaranja?: dayjs.Dayjs | null,
    public ponudes?: IPonude[] | null
  ) {}
}

export function getPostupciIdentifier(postupci: IPostupci): number | undefined {
  return postupci.id;
}
