import { IPonude } from 'app/entities/ponude/ponude.model';

export interface ISpecifikacije {
  id?: number;
  sifraPostupka?: number;
  brojPartije?: number;
  atc?: string | null;
  inn?: string | null;
  farmaceutskiOblikLijeka?: string | null;
  jacinaLijeka?: string | null;
  trazenaKolicina?: number;
  pakovanje?: string | null;
  jedinicaMjere?: string | null;
  procijenjenaVrijednost?: number;
  ponudes?: IPonude[] | null;
}

export class Specifikacije implements ISpecifikacije {
  constructor(
    public id?: number,
    public sifraPostupka?: number,
    public brojPartije?: number,
    public atc?: string | null,
    public inn?: string | null,
    public farmaceutskiOblikLijeka?: string | null,
    public jacinaLijeka?: string | null,
    public trazenaKolicina?: number,
    public pakovanje?: string | null,
    public jedinicaMjere?: string | null,
    public procijenjenaVrijednost?: number,
    public ponudes?: IPonude[] | null
  ) {}
}

export function getSpecifikacijeIdentifier(specifikacije: ISpecifikacije): number | undefined {
  return specifikacije.id;
}
