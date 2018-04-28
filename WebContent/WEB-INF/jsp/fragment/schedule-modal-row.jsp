<tr>
  <td>
    <i class="fa fa-trash-o fa-2x delete-schedule" aria-hidden="true"></i>
  </td>
  <td>
    <div class="form-group">
      <input type="text" class="form-control form-small" name="schedules[${index}].date" value="${schedule.date}" placeholder="yyyy-mm-dd" required>
    </div>
  </td>
  <td>
    <input type="text" class="form-control form-small" name="schedules[${index}].enterTime" value="${schedule.enterTime}" placeholder="hh:mm:ss">
  </td>
  <td>
    <input type="text" class="form-control form-small" name="schedules[${index}].startTime" value="${schedule.startTime}" placeholder="hh:mm:ss">
  </td>
  <td>
    <input type="text" class="form-control form-small" name="schedules[${index}].prefecture" value="${schedule.prefecture}">
  </td>
  <td>
    <div class="form-group">
      <input type="text" class="form-control form-big" name="schedules[${index}].place" value="${schedule.place}" required>
    </div>
  </td>
  <td>
    <textarea rows="2" class="form-control form-big" name="schedules[${index}].call">${schedule.call}</textarea>
  </td>
  <td>
    <textarea rows="2" class="form-control form-big" name="schedules[${index}].memo">${schedule.memo}</textarea>
  </td>
  <td>
    <input type="text" class="form-control form-big" name="schedules[${index}].link" value="${schedule.link}">
  </td>
</tr>