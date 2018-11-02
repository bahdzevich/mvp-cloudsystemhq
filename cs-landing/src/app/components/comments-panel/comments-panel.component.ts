import { Component, OnInit } from '@angular/core';
import {CommentElementSettings} from "./models/comment-element-settings";

@Component({
  selector: 'app-comments-panel',
  templateUrl: './comments-panel.component.html',
  styleUrls: ['./comments-panel.component.css']
})
export class CommentsPanelComponent implements OnInit {

  public commentsSettings: CommentElementSettings[] = [
    new CommentElementSettings("First Comment", "very good!"),
    new CommentElementSettings("Second Comment", "Not worry , be happy."),
  ];

  constructor() { }

  ngOnInit() {
  }

}
