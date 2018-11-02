import {Component, Input, OnInit} from '@angular/core';
import {CommentElementSettings} from "../../models/comment-element-settings";

@Component({
  selector: 'app-comment-element',
  templateUrl: './comment-element.component.html',
  styleUrls: ['./comment-element.component.css']
})
export class CommentElementComponent implements OnInit {

  @Input() public settings: CommentElementSettings;

  constructor() { }

  ngOnInit() {
  }

}
