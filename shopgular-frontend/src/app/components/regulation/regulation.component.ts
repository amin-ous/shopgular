import { Component, OnInit, TemplateRef } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Regulation } from 'src/app/models/regulation.model';
import { RegulationService } from 'src/app/services/regulation.service';

@Component({
  selector: 'app-regulation',
  templateUrl: './regulation.component.html',
  styleUrls: ['./regulation.component.css']
})

export class RegulationComponent implements OnInit {

  regulation!: Regulation;
  regulations!: Regulation[];

  constructor(private regulationService: RegulationService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.getAllRegulations();
  }

  getAllRegulations(): void {
    this.regulationService.getAllRegulations().subscribe((data: any) => this.regulations = data);
  }

  addRegulation(regulation: Regulation): void {
    this.regulationService.addRegulation(regulation).subscribe(() => this.getAllRegulations());
  }

  updateRegulation(regulation: Regulation): void {
    this.regulationService.updateRegulation(regulation).subscribe();
  }

  deleteRegulation(regulationId: number): void {
    this.regulationService.deleteRegulation(regulationId).subscribe(() => this.getAllRegulations());
  }

  openModal(content: TemplateRef<Regulation>, action: Regulation | null): void {
    action ? this.regulation = action : this.regulation = new Regulation();
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result;
  }

}
