import { IPostupci } from 'app/entities/postupci/postupci.model';
import { IPonudjaci } from 'app/entities/ponudjaci/ponudjaci.model';
import { ISpecifikacije } from 'app/entities/specifikacije/specifikacije.model';

export interface IPonude {
  id?: number;
  sifraPostupka?: number;
  sifraPonude?: number;
  brojPartije?: number;
  nazivProizvodjaca?: string | null;
  zasticeniNaziv?: string | null;
  ponudjanaKolicina?: number;
  ponudjenaVrijednost?: number;
  jedinicnaCijena?: number | null;
  rokIsporuke?: number;
  sifraPonudjaca?: number;
  selected?: boolean | null;
  postupci?: IPostupci | null;
  ponudjaci?: IPonudjaci | null;
  specifikacije?: ISpecifikacije | null;
}

export class Ponude implements IPonude {
  constructor(
    public id?: number,
    public sifraPostupka?: number,
    public sifraPonude?: number,
    public brojPartije?: number,
    public nazivProizvodjaca?: string | null,
    public zasticeniNaziv?: string | null,
    public ponudjanaKolicina?: number,
    public ponudjenaVrijednost?: number,
    public jedinicnaCijena?: number | null,
    public rokIsporuke?: number,
    public sifraPonudjaca?: number,
    public selected?: boolean | null,
    public postupci?: IPostupci | null,
    public ponudjaci?: IPonudjaci | null,
    public specifikacije?: ISpecifikacije | null
  ) {
    this.selected = this.selected ?? false;
  }
}

export function getPonudeIdentifier(ponude: IPonude): number | undefined {
  return ponude.id;
}
