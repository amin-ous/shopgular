import { Component, OnInit, TemplateRef } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Operator } from 'src/app/models/operator.model';
import { OperatorService } from 'src/app/services/operator.service';

@Component({
  selector: 'app-operator',
  templateUrl: './operator.component.html',
  styleUrls: ['./operator.component.css']
})

export class OperatorComponent implements OnInit {

  operator!: Operator;
  operators!: Operator[];

  constructor(private operatorService: OperatorService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.getAllOperators();
  }

  getAllOperators(): void {
    this.operatorService.getAllOperators().subscribe((data: any) => this.operators = data);
  }

  addOperator(operator: Operator): void {
    this.operatorService.addOperator(operator).subscribe(() => this.getAllOperators());
  }

  updateOperator(operator: Operator): void {
    this.operatorService.updateOperator(operator).subscribe();
  }

  deleteOperator(operatorId: number): void {
    this.operatorService.deleteOperator(operatorId).subscribe(() => this.getAllOperators());
  }

  openModal(content: TemplateRef<Operator>, action: Operator | null): void {
    action ? this.operator = action : this.operator = new Operator();
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result;
  }

}
